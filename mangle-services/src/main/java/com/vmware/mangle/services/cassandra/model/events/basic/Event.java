/*
 * Copyright (c) 2016-2019 VMware, Inc. All Rights Reserved.
 *
 * This product is licensed to you under the Apache License, Version 2.0 (the "License").
 * You may not use this product except in compliance with the License.
 *
 * This product may include a number of subcomponents with separate copyright notices
 * and license terms. Your use of these subcomponents is subject to the terms and
 * conditions of the subcomponent's license, as noted in the LICENSE file.
 */

package com.vmware.mangle.services.cassandra.model.events.basic;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.cassandra.core.mapping.Indexed;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 * Domain entity for Spring events.
 *
 * @author hkilari
 * @since 1.0
 */
@Data
@EqualsAndHashCode(exclude = "id")
public class Event implements Serializable {

    private static final long serialVersionUID = 1L;

    @PrimaryKey
    protected String id;

    @NotNull(message = "{errors.required}")
    @DateTimeFormat(iso = ISO.DATE)
    @Indexed
    protected LocalDate eventDate;

    @NotNull(message = "{errors.required}")
    @Size(min = 5, max = 30, message = "{errors.range}")
    protected String name;

    @NotNull(message = "{errors.required}")
    @Size(min = 5, max = 300, message = "{errors.range}")
    protected String message;

    public Event() {
        this.id = UUID.randomUUID().toString();
        this.name = "";
        this.message = "";
        this.eventDate = LocalDate.now();
    }

    public Event(String name, String message) {
        this();
        this.name = name;
        this.message = message;
    }
}
