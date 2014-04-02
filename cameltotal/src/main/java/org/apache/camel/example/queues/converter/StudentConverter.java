/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.camel.example.queues.converter;

import org.apache.camel.Converter;
import org.apache.camel.Exchange;
import org.apache.camel.TypeConverter;
//import org.apache.camel.example.jmstofile.queues.model.Student;

import java.math.BigDecimal;

/**
 * @version $Revision$
 */
@Converter
public final class StudentConverter {

//    @Converter
//    public static String personToString(Student student, Exchange exchange) {
//        TypeConverter converter = exchange.getContext().getTypeConverter();
//
//        String s = converter.convertTo(String.class, student);
//        if (s == null || s.length() < 30) {
//            throw new IllegalArgumentException("data is invalid");
//        }
//
//        s = s.replaceAll("##START##", "");
//        s = s.replaceAll("##END##", "");
//
//        String name = s.substring(0, 19).trim();
//        String lastName = s.substring(20, 26).trim();
//        String ageString = s.substring(27, 28).trim();
//        String wageString = s.substring(29, 32).trim();
//
//        BigDecimal wage = new BigDecimal(wageString).setScale(2);
//
//        Integer age = converter.convertTo(Integer.class, ageString);
//
//        Person person = new Person(name, lastName, age, wage);
//        return person;
//    }

}
