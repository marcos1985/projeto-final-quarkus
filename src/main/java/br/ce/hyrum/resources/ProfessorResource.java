package br.ce.hyrum.resources;

import javax.persistence.Entity;
import javax.validation.ConstraintViolationException;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ce.hyrum.dtos.ErrorResponse;
import br.ce.hyrum.dtos.professor.ProfessorRequestDto;
import br.ce.hyrum.services.ProfessorService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Path("/professores")
public class ProfessorResource {

    private final ProfessorService professorService;

    public ProfessorResource(ProfessorService professorService) {
        this.professorService = professorService;
    }

    @POST
    public Response save(ProfessorRequestDto professor) {

        try {
            log.info("Criando um novo professor.");
            var professorResponseDto = professorService.save(professor);
            return Response.status(Status.CREATED)
                    .entity(professorResponseDto)
  
                    .build();

        } catch(ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST)
                            .entity(ErrorResponse.createFromValidation(e))
                            .build();
        }
        
    }

    @GET
    public Response findAll() {

        log.info("Listando professores.");

        return Response
                    .ok(professorService.findAll())
                    .build();

    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Long id) {

        log.info("Listando professores.");

        return Response
                    .ok(professorService.findByID(id))
                    .build();

    }

    @PUT
    @Path("/{id}")
    public Response update( @PathParam("id") Long id, ProfessorRequestDto professor) {

        try {
            log.info("Atualizando professor de id " + id);

            return Response.ok()
                    .entity(professorService.update(id, professor))
                    .build();
    
        } catch(ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST)
                            .entity(ErrorResponse.createFromValidation(e))
                            .build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        
        log.info("Deletando professor de id " + id);
        professorService.delete(id);

        return Response
                    .ok()
                    .build();
    }


    @GET
    @Path("/{id}/tutorados")
    public Response findTutorados(@PathParam("id") Long id) {
        return Response.ok()
                    .entity(professorService.findTutorados(id))
                    .build();
    }
    
}
