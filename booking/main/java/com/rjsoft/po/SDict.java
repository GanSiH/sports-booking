package rjsoft.po;

import java.io.Serializable;
import lombok.Data;

/**
 * s_dict
 * @author 
 */
@Data
public class SDict implements Serializable {
    private Integer id;

    private String type;

    private String name;

    private String val;

    private String remarks;

    private static final long serialVersionUID = 1L;
}