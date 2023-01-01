package br.ce.hyrum.resources;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import br.ce.hyrum.dtos.aluno.AlunoRequestDto;
import br.ce.hyrum.services.AlunoService;

@Path("/alunos")
public class AlunoResource {

    @Inject
    AlunoService alunoService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAlunos() {
        return Response.ok(alunoService.getAlunos()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(AlunoRequestDto alunoRequestDto) {
        return Response.status(Status.CREATED).entity(alunoService.save(alunoRequestDto)).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long id , AlunoRequestDto alunoRequestDto) {
        return Response.ok().entity(alunoService.update(id, alunoRequestDto)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        alunoService.delete(id);
        return Response.ok()
                    .build(); 
    }
}
