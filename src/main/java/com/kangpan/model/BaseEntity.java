package com.kangpan.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Date;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class BaseEntity implements Serializable {
    private long id;
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date createTime;
    @JsonFormat(pattern="yyyy:MM:dd HH:mm:ss",timezone = "GMT+8")
    private Date updateTime;
    private String createdBy;
    private String updatedBy;

    @Override
    public String toString() {
        ToStringBuilder toStringBuilder = new ToStringBuilder(this);
            Field[] fields = this.getClass().getDeclaredFields();
        try {
            for(Field field : fields) {
                field.setAccessible(true);
                toStringBuilder.append(field.getName(), field.get(this)).append("\n");
            }
        } catch (Exception e) {
            toStringBuilder.append("toString builder encounter an error");
        }
        return toStringBuilder.toString();
    }
}
