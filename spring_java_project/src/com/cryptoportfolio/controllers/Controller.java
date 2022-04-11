package com.cryptoportfolio.controllers;

import com.cryptoportfolio.App;
import com.cryptoportfolio.activity.GetPortfolioActivity;
import com.cryptoportfolio.activity.RegisterActivity;
import com.cryptoportfolio.dependency.ServiceComponent;
import com.cryptoportfolio.dynamodb.models.User;
import com.cryptoportfolio.models.requests.GetPortfolioRequest;
import com.cryptoportfolio.models.requests.RegisterRequest;
import com.cryptoportfolio.models.responses.RegisterResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import org.mindrot.jbcrypt.BCrypt;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import javax.validation.Valid;

@RestController
public class Controller {
    private static final ServiceComponent component = App.component;


    @PostMapping(value = "/register", consumes = {"application/json"}, produces = {"application/json"})
    public ResponseEntity<?> RegisterActivity(@Valid @RequestBody RegisterRequest registerRequest) {

        RegisterActivity registerActivity = component.provideRegisterActivity();

        RegisterResponse response = registerActivity.execute(registerRequest);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }


//    @PostMapping(value = "/register", consumes = {"application/json"}, produces = {"application/json"})
//    public ResponseEntity<?> RegisterActivity(@Valid @RequestBody RegisterRequest registerRequest) {
//
//        RegisterActivity registerActivity = component.provideRegisterActivity();
//
//        RegisterResponse response = registerActivity.execute(registerRequest);
//
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }

//    @GetMapping(value = "/portfolio/{id}", produces = {"application/json"})
//    public ResponseEntity<?> getPortfolioActivity(@PathVariable String id) {
//        GetPortfolioActivity getPortfolioActivity = component.provideGetPortfolioActivity();
//        GetPortfolioRequest getPortfolioRequest = GetPortfolioRequest.builder().username(id).build();
//        System.out.println("Request : " + getPortfolioRequest);
//        System.out.println("Response : " + getPortfolioActivity.execute(getPortfolioRequest));
//        return new ResponseEntity<>(getPortfolioActivity.execute(getPortfolioRequest), HttpStatus.OK);
//
//    }

//    @DeleteMapping(value = "/books/{id}")
//    public ResponseEntity<?> removeBook(@PathVariable String id) {
//        RemoveBookFromCatalogActivity bookActivity = component.provideRemoveBookFromCatalogActivity();
//        RemoveBookFromCatalogRequest removeBookRequest = RemoveBookFromCatalogRequest.builder().withBookId(id).build();
//        return new ResponseEntity<>(bookActivity.execute(removeBookRequest), HttpStatus.OK);
//    }
//
//    @PostMapping(value = "/books", consumes = {"application/json"}, produces = {"application/json"})
//    public ResponseEntity<?> submitBookForPublishing(@Valid @RequestBody Book book) {
//
//        System.out.println("Submit request");
//
//        SubmitBookForPublishingActivity submitBookForPublishingActivity = component.provideSubmitBookForPublishingActivity();
//
//        SubmitBookForPublishingRequest submitBookForPublishingRequest = SubmitBookForPublishingRequest.builder()
//                                                                        .withBookId(book.getBookId())
//                                                                        .withTitle(book.getTitle())
//                                                                        .withAuthor(book.getAuthor())
//                                                                        .withGenre(book.getGenre())
//                                                                        .withText(book.getText())
//                                                                        .build();
//
//        SubmitBookForPublishingResponse response = submitBookForPublishingActivity.execute(submitBookForPublishingRequest);
//        //return new ResponseEntity<>(submitBookForPublishingActivity.execute(submitBookForPublishingRequest), HttpStatus.OK);
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
//
//    @GetMapping(value = "/books/publishingStatus/{id}", produces = {"application/json"})
//    public ResponseEntity<?> getPublishingStatus(@PathVariable String id) {
//        GetPublishingStatusActivity publishingStatusActivity = component.provideGetPublishingStatusActivity();
//        GetPublishingStatusRequest getPublishingStatusRequest = GetPublishingStatusRequest.builder()
//                                                                    .withPublishingRecordId(id).build();
//        return new ResponseEntity<>(publishingStatusActivity.execute(getPublishingStatusRequest), HttpStatus.OK);
//    }
}
