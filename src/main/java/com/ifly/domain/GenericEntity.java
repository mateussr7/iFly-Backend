package com.ifly.domain;

import javax.persistence.Transient;
import java.io.Serializable;

public class GenericEntity implements Serializable {
    @Transient
    private static final long serialVersionUID = 1L;
}
