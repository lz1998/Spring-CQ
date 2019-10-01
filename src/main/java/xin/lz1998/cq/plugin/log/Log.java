package xin.lz1998.cq.plugin.log;


import lombok.Builder;
import lombok.Data;

import java.util.Date;
// 本来用于JPA记录日志
//@Entity
//@Table
@Data
@Builder
public class Log {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

//    @Column
    private Long selfId;

//    @Column
    private String type;

//    @Column
    private Long groupId;

//    @Column
    private Long userId;

//    @Column
    private Long operatorId;

//    @Column(length = 8000)
    private String content;

//    @CreationTimestamp
//    @Column(updatable = false,nullable = false)
    private Date time;
}
