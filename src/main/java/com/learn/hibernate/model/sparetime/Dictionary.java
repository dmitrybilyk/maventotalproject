package com.learn.hibernate.model.sparetime;

import java.io.Serializable;

public interface Dictionary extends HasName, Serializable {

    String getIdAsString();

    void setIdAsString(String id);


}
