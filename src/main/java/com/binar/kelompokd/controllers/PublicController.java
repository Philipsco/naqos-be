package com.binar.kelompokd.controllers;

import com.binar.kelompokd.interfaces.KostService;
import com.binar.kelompokd.models.entity.kost.Kost;
import com.binar.kelompokd.utils.response.PageResponse;
import com.binar.kelompokd.utils.response.Response;
import com.binar.kelompokd.utils.SimpleStringUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;


@AllArgsConstructor
@RestController
@RequestMapping("/public")
@Tag(name = "Public Management", description = "APIs for Managing Public Consumes")
public class PublicController {
  private final static Logger logger = LoggerFactory.getLogger(PublicController.class);
  @Autowired
  KostService kostService;
  @Autowired
  SimpleStringUtils simpleStringUtils;
  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  @Autowired
  Response Response;

  @Operation(summary = "Get All List Kosts", tags = {"Public Management"})
  @GetMapping("/get")
  public ResponseEntity<?> getAllKosts() {
    return new ResponseEntity<>(Response.templateSukses(kostService.getAllKost()), HttpStatus.OK);
  }

  @Operation(summary = "Get List Kosts By City Id", tags = {"Public Management"})
  @GetMapping("/city-id/{cityId}")
  public ResponseEntity<?> getKostsByCityId(
      @RequestParam() @Schema(example = "1") int page,
      @RequestParam() @Schema(example = "10") int size,
      @RequestParam(required = false, defaultValue = "id") @Schema(example = "id") String orderBy,
      @RequestParam(required = false, defaultValue = "desc") @Schema(example = "desc") String orderType,
      @PathVariable("cityId") @Schema(example = "1") Integer cityId) {
    Pageable pageable = simpleStringUtils.getShort(orderBy, orderType, page - 1, size);
    Page<Kost> kosts = kostService.getKostsByCityId(cityId, pageable);
    logger.info("list Kosts", kosts);
    PageResponse response = new PageResponse(
        kosts.getTotalPages(),
        kosts.getTotalElements(),
        page,
        kosts.isFirst(),
        kosts.isLast(),
        size,
        OBJECT_MAPPER.convertValue(kosts.getContent(), List.class));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get List Kosts by City Name", tags = {"Public Management"})
  @GetMapping("/by-city/{cityName}")
  public ResponseEntity<?> getKostsByCityName(
      @RequestParam() @Schema(example = "1") int page,
      @RequestParam() @Schema(example = "10") int size,
      @RequestParam(required = false, defaultValue = "id") @Schema(example = "id") String orderBy,
      @RequestParam(required = false, defaultValue = "desc") @Schema(example = "desc") String orderType,
      @PathVariable("cityName") @Schema(example = "Kabupaten Lebak") String cityName) {
    Pageable pageable = simpleStringUtils.getShort(orderBy, orderType, page - 1, size);
    Page<Kost> kosts = kostService.getKostsByCity(cityName, pageable);
    logger.info("list Kosts By City", kosts);
    PageResponse response = new PageResponse(
        kosts.getTotalPages(),
        kosts.getTotalElements(),
        page,
        kosts.isFirst(),
        kosts.isLast(),
        size,
        OBJECT_MAPPER.convertValue(kosts.getContent(), List.class));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get All List Kosts with Pagination", tags = {"Public Management"})
  @GetMapping("/page")
  public ResponseEntity<?> getAllKostsWithPaginationAndFilter(@RequestParam() @Schema(example = "1") int page, @RequestParam() @Schema(example = "10") int size, @RequestParam(required = false, defaultValue = "id") @Schema(example = "id") String orderBy, @RequestParam(required = false, defaultValue = "desc") @Schema(example = "desc") String orderType) {
    Pageable pageable = simpleStringUtils.getShort(orderBy, orderType, page - 1, size);
    Page<Kost> kosts = kostService.getListData(pageable);
    logger.info("list Kosts page", kosts);
    PageResponse response = new PageResponse(
        kosts.getTotalPages(),
        kosts.getTotalElements(),
        page,
        kosts.isFirst(),
        kosts.isLast(),
        size,
        OBJECT_MAPPER.convertValue(kosts.getContent(), List.class));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get List Kosts by Kost Type", tags = {"Public Management"})
  @GetMapping("/by-type/page")
  public ResponseEntity<?> getKostsByKostType(
      @RequestParam() @Schema(example = "1") int page,
      @RequestParam() @Schema(example = "10") int size,
      @RequestParam(required = false, defaultValue = "id") @Schema(example = "id") String orderBy,
      @RequestParam(required = false, defaultValue = "desc") @Schema(example = "desc") String orderType,
      @RequestParam() @Schema(example = "KOS_PUTRA") String kostType) {
    Pageable pageable = simpleStringUtils.getShort(orderBy, orderType, page - 1, size);
    Page<Kost> kosts = kostService.getKostsByKostType(kostType, pageable);
    logger.info("getKostsByKostType", kosts);
    PageResponse response = new PageResponse(
        kosts.getTotalPages(),
        kosts.getTotalElements(),
        page, kosts.isFirst(),
        kosts.isLast(),
        size,
        OBJECT_MAPPER.convertValue(kosts.getContent(), List.class));
    return new ResponseEntity<>(response, HttpStatus.OK);
  }

  @Operation(summary = "Get Kost by Id", tags = {"Public Management"})
  @GetMapping("/{id}")
  public ResponseEntity<?> getKostById(@PathVariable("id") @Schema(example = "123e4567-e89b-12d3-a456-426614174000") UUID id){

    try {
      Kost kost = kostService.getKostById(id);
      return new ResponseEntity<>(Response.templateSukses(kost), HttpStatus.OK);
    }
    catch (NoSuchElementException noSuchElementException){
      logger.error("Kost tidak ada", noSuchElementException);
      return new ResponseEntity<>(Response.notFound("Kos doesn't exist"), HttpStatus.NOT_FOUND);
    }
  }
}
