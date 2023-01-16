package com.binar.kelompokd.models.request;

import com.binar.kelompokd.enums.Condition;
import com.binar.kelompokd.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class KostRoomRequest {
    @NotNull
    @Enumerated(EnumType.STRING)
    private RoomType room_type;

    // rules text
    @Column(columnDefinition="TEXT")
    @NotNull
    private String rules;

    @NotNull
    private BigDecimal price_per_daily;

    @NotNull
    private BigDecimal price_per_weekly;

    @NotNull
    private BigDecimal price_per_monthly;

    @NotNull
    private Boolean is_available;

    // facility_id array[]
    @NotNull
    private UUID[] facility_id;

    // image_id array[]
    @NotNull
    private Integer[] image_id;
}
