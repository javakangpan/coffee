package com.kangpan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity implements Serializable {
    private long id;
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    @NotNull(message = "")
    private Date createTime;
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    @NotNull(message = "")
    private Date updateTime;
    @NotEmpty
    private String createdBy;
    @NotEmpty
    private String updatedBy;
}
