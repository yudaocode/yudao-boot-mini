package com.muang.ai.claw.module.infra.entity.file;

import com.muang.ai.claw.common.core.BaseEntity;
import com.muang.ai.claw.config.tenant.core.aop.TenantIgnore;
import com.muang.ai.claw.config.file.core.client.db.DBFileClient;
import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

/**
 * 文件内容表
 *
 * 专门用于存储 {@link DBFileClient} 的文件内容
 *
 */
@TableName("infra_file_content")
@KeySequence("infra_file_content_seq") // 用于 Oracle、PostgreSQL、Kingbase、DB2、H2 数据库的主键自增。如果是 MySQL 等数据库，可不写。
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Builder
@NoArgsConstructor
@AllArgsConstructor
@TenantIgnore
public class FileContentEntity extends BaseEntity {

    /**
     * 编号，数据库自增
     */
    @TableId
    private Long id;
    /**
     * 配置编号
     *
     * 关联 {@link FileConfigEntity#getId()}
     */
    private Long configId;
    /**
     * 路径，即文件名
     */
    private String path;
    /**
     * 文件内容
     */
    private byte[] content;

}
