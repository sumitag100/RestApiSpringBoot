package io.javabrains.movieinfoservice.resorces;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/movies")

public class MovieResource {

	@Value("${api.key}")
	private String apiKey;

	@Autowired
	private RestTemplate restTemplate;

	@RequestMapping("/{movieid}")
	public Movie getMovieInformation(@PathVariable String movieid) {
		MovieSummary movieSummary = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/76341" + movieid + "?api_key=" + "ec677df48d78463ee373fb69edefe4b3", MovieSummary.class);
		return new Movie(movieid, movieSummary.getTitle(), movieSummary.getOverview());

	}

}
