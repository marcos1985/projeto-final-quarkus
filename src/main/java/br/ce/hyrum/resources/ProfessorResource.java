package br.ce.hyrum.resources;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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
    public Response create(@Valid ProfessorRequestDto professor) {

        try {
            log.info("Criando um novo professor.");
            var professorRequestDto = professorService.save(professor);
            return Response.status(Status.CREATED)
                    .entity(professorRequestDto)
                    .build();

        } catch(Exception e) {
            return Response.status(Status.INTERNAL_SERVER_ERROR)
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

    @PUT
    @Path("/{id}")
    public Response atualizar( @PathParam("id") Long id, ProfessorRequestDto professor) {

        log.info("Atualizando professor de id " + id);

        return Response.ok()
                    .entity(professorService.update(id, professor))
                    .build();
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
    
}
