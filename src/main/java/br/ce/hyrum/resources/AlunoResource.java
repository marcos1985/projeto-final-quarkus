package br.ce.hyrum.resources;

import javax.inject.Inject;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ce.hyrum.dtos.ErrorResponse;
import br.ce.hyrum.dtos.aluno.AlunoRequestDto;
import br.ce.hyrum.services.AlunoService;
import lombok.AllArgsConstructor;

@Path("/alunos")
@AllArgsConstructor
public class AlunoResource {

    
    private AlunoService alunoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAll() {
        return Response.ok(alunoService.findAll()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response save(AlunoRequestDto alunoRequestDto) {

        try {

            return Response.
                        status(Status.CREATED)
                        .entity(alunoService.save(alunoRequestDto))
                        .build();

        } catch(ConstraintViolationException e) {
            return Response.status(Status.BAD_REQUEST)
                            .entity(ErrorResponse.createFromValidation(e))
                            .build();
        }

    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id , AlunoRequestDto alunoRequestDto) {
        
        try {

            return Response.ok()
                           .entity(alunoService.update(id, alunoRequestDto))
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
        alunoService.delete(id);
        return Response.ok()
                    .build(); 
    }

    @PATCH
    @Path("/{id}/professor/{idProfessor}")
    public Response tutor(@PathParam("id") Long id, @PathParam("idProfessor") Long idProfessor) {
        alunoService.setTutor(id, idProfessor);
        return Response.ok()
                .build();
    }
}
