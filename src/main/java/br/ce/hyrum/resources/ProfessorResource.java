package br.ce.hyrum.resources;

import javax.inject.Inject;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.ce.hyrum.dtos.ProfessorDto;
import br.ce.hyrum.services.ProfessorService;

@Path("/professores")
public class ProfessorResource {

    private final ProfessorService professorService;

    public ProfessorResource(ProfessorService professorService) {
        this.professorService = professorService;
    }

    private static final Logger log = LoggerFactory.getLogger(ProfessorResource.class);

    @POST
    public Response criar(ProfessorDto professor) {

        log.info("Criando um novo professor.");

        return Response.status(Status.CREATED)
                    .entity(professorService.salvar(professor))
                    .build();

    }

    @GET
    public Response getProfessores() {

        log.info("Listando professores.");

        return Response
                    .ok(professorService.getProfessores())
                    .build();

    }

    @PUT
    @Path("/{id}")
    public Response atualizar( @PathParam("id") Integer id, ProfessorDto professor) {

        log.info("Listando professor de id " + id);

        return Response.ok()
                    .entity(professorService.atualizar(id, professor))
                    .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deletar(@PathParam("id") Integer id) {
        
        log.info("Deletando professor de id " + id);
        professorService.deletar(id);

        return Response
                    .ok()
                    .build();
    }
    
}
