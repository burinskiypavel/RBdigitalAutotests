package Gson;
import com.google.gson.*;

public class PapiMain {
    public static void main(String[] args) {
        //String jsonString = String.format("{\n    \"firstName\": \"Json\",\n    \"lastName\": \"Smith\",\n    \"age\": 30,\n    \"address\": {\n        \"streetAddress\": \"666 1nd Street\",\n        \"city\": \"New York\",\n        \"state\": \"NY\",\n        \"postalCode\": 10021\n    },\n    \"phoneNumbers\": [\n        {\n            \"type\": \"home\",\n            \"number\": \"542 666-1234\"\n        },\n        {\n            \"type\": \"fax\",\n            \"number\": \"653 666-4567\" \n        }\n    ],\n    \"friends\": [\n        {\n            \"firstName\": \"Test\",\n            \"lastName\": \"Snow\",\n            \"age\": 20,\n            \"phoneNumbers\": [\n                {\n                    \"type\": \"home\",\n                    \"number\": \"141 111-1234\"\n                }\n            ],\n            \"friends\": [\n                {\n                    \"firstName\": \"UnknownFirstName\",\n                    \"lastName\": \"UnknownLastName\",\n                    \"age\": 999,\n                    \"phoneNumbers\": [\n                        {\n                            \"type\": \"home\",\n                            \"number\": \"000 000-0000\"\n                        }\n                    ]\n                }\n            ]\n        },\n        {\n            \"firstName\": \"Flash\",\n            \"lastName\": \"Tompson\",\n            \"age\": 23,\n            \"phoneNumbers\": [\n                {\n                    \"type\": \"home\",\n                    \"number\": \"999 111-1234\"\n                }\n            ]\n        }\n    ]\n} ");
        String jsonString = "{\n" +
                "    \"description\": \"Standardizes the definition and framework of analytics ABOK stands for Analytics Body of Knowledge. Based on the authors' definition of analytics-which is \\\"a process by which a team of people helps an organization make better decisions (the objective) through the analysis of data (the activity)\\\"-this book from Institute for Operations Research and the Management Sciences (INFORMS) represents the perspectives of some of the most respected experts on analytics. The INFORMS ABOK documents the core concepts and skills with which an analytics professional should be familiar; establishes a dynamic resource that will be used by practitioners to increase their understanding of analytics; and, presents instructors with a framework for developing academic courses and programs in analytics. The INFORMS ABOK offers in-depth insight from peer-reviewed chapters that provide readers with a better understanding of the dynamic field of analytics. Chapters cover: Introduction to Analytics; Getting Started with Analytics; The Analytics Team; The Data; Solution Methodology; Model Building; Machine Learning; Deployment and Life Cycle Management; and The Blossoming Analytics Talent Pool: An Overview of the Analytics Ecosystem. Across industries and academia, readers with various backgrounds in analytics - from novices who are interested in learning more about the basics of analytics to experienced professionals who want a different perspective on some aspect of analytics - will benefit from reading about and implementing the concepts and methods covered by the INFORMS ABOK. If you would like to learn more about the Wiley Series in Operations Research and Management Science or if you would like to write for the series, please visit: https://www.wiley.com/WileyCDA/Sectiond-814058.html.\",\n" +
                "    \"publisher\": \"Wiley\",\n" +
                "    \"audience\": \"Adult\",\n" +
                "    \"genres\": \"MATHEMATICS / Probability & Statistics / General\",\n" +
                "    \"primaryGenre\": \"science\",\n" +
                "    \"isbn\": \"9781119488255\",\n" +
                "    \"mediaType\": \"eBook\",\n" +
                "    \"title\": \"INFORMS Analytics Body of Knowledge\",\n" +
                "    \"authors\": \"James J.  Cochran\",\n" +
                "    \"language\": \"English\",\n" +
                "    \"images\": [\n" +
                "        {\n" +
                "            \"name\": \"small\",\n" +
                "            \"url\": \"https://d2cv0ie6dlin9h.cloudfront.net/EB00737238/EB00737238_image_71x108.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"medium\",\n" +
                "            \"url\": \"https://d2cv0ie6dlin9h.cloudfront.net/EB00737238/EB00737238_image_95x140.jpg\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"name\": \"large\",\n" +
                "            \"url\": \"https://d2cv0ie6dlin9h.cloudfront.net/EB00737238/EB00737238_image_128x192.jpg\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"titleId\": 438086\n" +
                "}";



        Gson g = new Gson();

        Response response = g.fromJson(jsonString, Response.class);

        //     for(Person friend : person.friends) {
        //   System.out.print(friend.lastName);
        //  for(Phones phone : friend.phoneNumbers) {
        //       System.out.println(" - phone type: " + phone.type + ", phone number :" + phone.number);
        //  }


        for(Response img : response.images) {
            System.out.println(img.url);
            System.out.println(img.name);
            //System.out.print(friend.lastName);
            //for(Phones phone : friend.phoneNumbers) {
            //    System.out.println(" - phone type: " + phone.type + ", phone number :" + phone.number);
            }

        }
    }


