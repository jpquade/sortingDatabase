package com.sorting.demo.controller;


import com.sorting.demo.entity.SortEntity;
import com.sorting.demo.exception.ResourceNotFoundException;
import com.sorting.demo.service.SortService;

import com.sorting.demo.utility.DataSet;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * The SortingController class is the main controller used
 * to mass generate,get, delete, modify, and single instances of
 * data into a list of created sorting data.
 */
@Controller
@RequestMapping("/testsite")
public class SortController {

    private final SortService sortService;

    /**
     * Instantiates a new SortingService to enable
     * each of the services available to the app.
     *
     * @param sortService the sort service
     */
    public SortController(SortService sortService) {
        this.sortService = sortService;
    }

    /**
     * Api "addressing" for retrieving the entire database list of items added
     * in webpage format.
     *
     * @param theModel the model used to create a list in the webpage
     * @return web address where data is displayed
     */
    @GetMapping("/getList")
    public String listSorts(Model theModel){

        List<SortEntity> theSort = sortService.findAll();

        theModel.addAttribute("sorts", theSort);

        return "/sorts/sorts.html";
    }

    /**
     * Retrieving the entire database list of items added
     * in a raw list format
     *
     * @return response delivered when successful delivery of
     * the list
     */
    @GetMapping("/getListRaw")
    public ResponseEntity<?> getlistSorts(){

        List<SortEntity> list = new ArrayList<>(sortService.findAll());

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    /**
     * Retrieves an individual database item with a valid id
     *
     * @param sortId id used to search for a database entry
     * @return the response delivered when successful retrieval
     * of item from list
     */
    @GetMapping("/getId/{id}")
    public ResponseEntity<SortEntity> getId(@PathVariable("id") int sortId){
        Optional<SortEntity> result = sortService.findId(sortId);

        SortEntity sortedItem;

        if(result.isPresent()) sortedItem = result.get();
        else{
            throw new RuntimeException("Did not find sorted Id");
        }

        return new ResponseEntity<>(sortedItem, HttpStatus.OK);
    }

    /**
     * Delete a database entry with a valid id.
     *
     * @param sortId id used to search for a database entry
     * @return the response delivered when successful delete
     * command processed
     */
    @DeleteMapping("/delete/{sortId}")
    public ResponseEntity<String> delete(@PathVariable int sortId){

        Optional<SortEntity> result = sortService.findId(sortId);

        if(result.isEmpty()){
            return new ResponseEntity<>("Did not find sorted ID - " + Integer.toString(sortId), HttpStatus.NOT_FOUND);
        }

        sortService.deleteById(sortId);

        return new ResponseEntity<>("Item deleted  id - " + Integer.toString(sortId), HttpStatus.OK);
    }

    /**
     * Update database entry using a valid entry
     *
     * @param sortId     id used to search for a database entry
     * @param sortDetail item info containing update
     * @return the response delivered when successful update is processed
     */
    @PutMapping("/update/{sortId}")
    public ResponseEntity<SortEntity> update(@PathVariable int sortId, @RequestBody SortEntity sortDetail){

        SortEntity updateSort = sortService.findId(sortId)
                .orElseThrow(() -> new ResourceNotFoundException("Sorting id not found: " +sortId));

        updateSort.setSortName(sortDetail.getSortName());
        updateSort.setGroupNumber(sortDetail.getGroupNumber());
        updateSort.setTime(sortDetail.getTime());

        sortService.save(updateSort);

        return new ResponseEntity<>(updateSort, HttpStatus.OK);
    }

    /**
     * Add a database entry
     *
     * @param sortDetail item info containing data to be added
     * @return the response delivered when successful add is processed
     */
    @PostMapping("/add")
    public ResponseEntity<String> addEntry(@RequestBody SortEntity sortDetail){

        sortService.save(sortDetail);

        return new ResponseEntity<>("Item added", HttpStatus.OK);
    }

    /**
     * Number of data elements is entered by user as well as the
     * number of program loops which determines how many times each set
     * of sorting algorithms is run.
     *
     * @param info input received for the dataSize and number of loops
     * @return the response when data commands were received
     * @throws ExecutionException   exception when data fails to be added to the database
     * @throws InterruptedException interrupted exception happens when a failure to add database occurs
     */
    @PostMapping("/compute")
    public ResponseEntity<String> computeData(@RequestBody DataSet info) throws ExecutionException, InterruptedException {

        sortService.calculateSorting(info.getDataSize(), info.getLoops());

        return new ResponseEntity<>("Inserted Sorted Entry data", HttpStatus.OK);
    }
}
