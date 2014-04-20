package com.learn.bases.appacheutils.tostringbuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringStyle;
 
public class CustomToStringStyle extends ToStringStyle
{
    private static final long serialVersionUID = 1L;
    protected void appendDetail(StringBuffer buffer, String fieldName, Object value)
    {
         if (value instanceof Date)
         {
             value = new SimpleDateFormat("yyyy-MM-dd").format(value);
         }
         buffer.append(value);
     }
}