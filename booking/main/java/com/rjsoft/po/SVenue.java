package rjsoft.po;

import java.io.Serializable;
import lombok.Data;

/**
 * s_venue
 * @author 
 */
@Data
public class SVenue implements Serializable {
    private Integer id;

    /**
     * 名称
     */
    private String name;

    private String val;

    private Integer parentId;

    private static final long serialVersionUID = 1L;
}