package resources;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import model.*;
import services.*;


@Path("{Bsn}")
public class gezinslidResource {	
	@GET
	@Produces("application/json")
	public String getActiviteitenGezinslid(@PathParam("Bsn") int Bsn) {
		gezinslidService service = ServiceProvider.glService();
		JsonArrayBuilder jab = Json.createArrayBuilder();
		for (Activiteit a : service.getAlleActiviteitenGezinslid(Bsn)) {			
			JsonObjectBuilder job = Json.createObjectBuilder();
			job.add("ActiviteitID", a.getActiviteitID());
			job.add("ActiviteitNaam", a.getActiviteitNaam());
			job.add("Omschrijving", a.getOmschrijving());
			job.add("GezinslidNaam", service.getGezinslid(Bsn).getGezinslidNaam());
			jab.add(job);
		}
		JsonArray array = jab.build();
		return array.toString();
	}
}
