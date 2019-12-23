package Gson;
import com.google.gson.*;

public class Test {
        public static void main(String[] args) {
            //String jsonString = String.format("{\n    \"firstName\": \"Json\",\n    \"lastName\": \"Smith\",\n    \"age\": 30,\n    \"address\": {\n        \"streetAddress\": \"666 1nd Street\",\n        \"city\": \"New York\",\n        \"state\": \"NY\",\n        \"postalCode\": 10021\n    },\n    \"phoneNumbers\": [\n        {\n            \"type\": \"home\",\n            \"number\": \"542 666-1234\"\n        },\n        {\n            \"type\": \"fax\",\n            \"number\": \"653 666-4567\" \n        }\n    ],\n    \"friends\": [\n        {\n            \"firstName\": \"Test\",\n            \"lastName\": \"Snow\",\n            \"age\": 20,\n            \"phoneNumbers\": [\n                {\n                    \"type\": \"home\",\n                    \"number\": \"141 111-1234\"\n                }\n            ],\n            \"friends\": [\n                {\n                    \"firstName\": \"UnknownFirstName\",\n                    \"lastName\": \"UnknownLastName\",\n                    \"age\": 999,\n                    \"phoneNumbers\": [\n                        {\n                            \"type\": \"home\",\n                            \"number\": \"000 000-0000\"\n                        }\n                    ]\n                }\n            ]\n        },\n        {\n            \"firstName\": \"Flash\",\n            \"lastName\": \"Tompson\",\n            \"age\": 23,\n            \"phoneNumbers\": [\n                {\n                    \"type\": \"home\",\n                    \"number\": \"999 111-1234\"\n                }\n            ]\n        }\n    ]\n} ");
            String jsonString = "{\n" +
                    "    \"firstName\": \"Json\",\n" +
                    "    \"lastName\": \"Smith\",\n" +
                    "    \"age\": 30,\n" +
                    "    \"address\": {\n" +
                    "        \"streetAddress\": \"666 1nd Street\",\n" +
                    "        \"city\": \"New York\",\n" +
                    "        \"state\": \"NY\",\n" +
                    "        \"postalCode\": 10021\n" +
                    "    },\n" +
                    "    \"phoneNumbers\": [\n" +
                    "        {\n" +
                    "            \"type\": \"home\",\n" +
                    "            \"number\": \"542 666-1234\"\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"type\": \"fax\",\n" +
                    "            \"number\": \"653 666-4567\" \n" +
                    "        }\n" +
                    "    ],\n" +
                    "    \"friends\": [\n" +
                    "        {\n" +
                    "            \"firstName\": \"Test\",\n" +
                    "            \"lastName\": \"Snow\",\n" +
                    "            \"age\": 20,\n" +
                    "            \"phoneNumbers\": [\n" +
                    "                {\n" +
                    "                    \"type\": \"home\",\n" +
                    "                    \"number\": \"141 111-1234\"\n" +
                    "                }\n" +
                    "            ],\n" +
                    "            \"friends\": [\n" +
                    "                {\n" +
                    "                    \"firstName\": \"UnknownFirstName\",\n" +
                    "                    \"lastName\": \"UnknownLastName\",\n" +
                    "                    \"age\": 999,\n" +
                    "                    \"phoneNumbers\": [\n" +
                    "                        {\n" +
                    "                            \"type\": \"home\",\n" +
                    "                            \"number\": \"000 000-0000\"\n" +
                    "                        }\n" +
                    "                    ]\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        },\n" +
                    "        {\n" +
                    "            \"firstName\": \"Flash\",\n" +
                    "            \"lastName\": \"Tompson\",\n" +
                    "            \"age\": 23,\n" +
                    "            \"phoneNumbers\": [\n" +
                    "                {\n" +
                    "                    \"type\": \"home\",\n" +
                    "                    \"number\": \"999 111-1234\"\n" +
                    "                }\n" +
                    "            ]\n" +
                    "        }\n" +
                    "    ]\n" +
                    "}";



        Gson g = new Gson();

        Person person = g.fromJson(jsonString, Person.class);

                     for(Person friend : person.friends) {
                          System.out.print(friend.firstName);
                          System.out.print(friend.lastName);
                          for(Phones phone : friend.phoneNumbers) {
                              System.out.println(" - phone type: " + phone.type + ", phone number :" + phone.number);
                          }



        }
    }

}
