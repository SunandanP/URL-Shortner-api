package com.tryout.demo.controller;

import com.tryout.demo.db.UrlMapping;
import com.tryout.demo.db.UrlRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.rng.UniformRandomProvider;
import org.apache.commons.rng.simple.RandomSource;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

// declaration of a class which will handle the urls
@RestController
public class UrlController {

    // declaration of a dependency that is required to run this class
    // in this case it is a class which communicates to the database
    @Autowired // this annotation specifies that this dependency will be managed by spring framework
    UrlRepository repository;

    @GetMapping("") // for get request with /
    public String getHome(){
        return "hello";
    }

    @GetMapping("{url}") // for get request with /{some value of url of type string}
    public void method(HttpServletResponse httpServletResponse, @PathVariable String url) {
        try {
            // httpServletResponse manages the responses to be provided, here it can also redirect to external web pages (I learned this today!)
            httpServletResponse.sendRedirect(repository.getRedirection(url));
            // repository.getRedirection(url) will have url as the parameter and repository will talk to database and finds the entry for which encoded url matches.
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // redirection successful status code
        httpServletResponse.setStatus(302);
    }

    @PostMapping("")
    public UrlMapping generateUrl(@RequestBody String url){
        UniformRandomProvider rng = RandomSource.ISAAC.create();
        // for generation of random urls we use apache's predefined codes for building random urls
        RandomStringGenerator generator = new RandomStringGenerator.Builder()
                .withinRange('A', 'z')
                .usingRandom(rng::nextInt) // uses Java 8 syntax
                .build();
        String randomLetters = generator.generate(7);

        // we create a dummy Object and fill it with the values that we require
        UrlMapping urlMapping = new UrlMapping(0, url.replace("\"", ""), randomLetters);
        // there are some characters which are added when we fire up this POST request, so to trim those chars we have replace function replacing them with nothing.
        repository.add(urlMapping); // this method asks the repo class to further add this object to the database
        return repository.getParticularMapping(urlMapping); // return the same thing as an acknowledgement
    }
}
