package Gson;

import com.google.gson.Gson;

public class GoogleMain3 {

    public static void main(String[] args) {
        String jsonString = "{\n" +
                "    \"widgets\": [\n" +
                "        {\n" +
                "            \"request\": {\n" +
                "                \"time\": \"2014-12-16 2019-12-16\",\n" +
                "                \"resolution\": \"WEEK\",\n" +
                "                \"locale\": \"ru\",\n" +
                "                \"comparisonItem\": [\n" +
                "                    {\n" +
                "                        \"geo\": {\n" +
                "                            \"country\": \"US\"\n" +
                "                        },\n" +
                "                        \"complexKeywordsRestriction\": {\n" +
                "                            \"keyword\": [\n" +
                "                                {\n" +
                "                                    \"type\": \"BROAD\",\n" +
                "                                    \"value\": \"american eagle\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"geo\": {\n" +
                "                            \"country\": \"US\"\n" +
                "                        },\n" +
                "                        \"complexKeywordsRestriction\": {\n" +
                "                            \"keyword\": [\n" +
                "                                {\n" +
                "                                    \"type\": \"BROAD\",\n" +
                "                                    \"value\": \"aerie\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"requestOptions\": {\n" +
                "                    \"property\": \"\",\n" +
                "                    \"backend\": \"IZG\",\n" +
                "                    \"category\": 0\n" +
                "                }\n" +
                "            },\n" +
                "            \"lineAnnotationText\": \"Уровень интереса к теме\",\n" +
                "            \"bullets\": [\n" +
                "                {\n" +
                "                    \"text\": \"american eagle\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"text\": \"aerie\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"showLegend\": false,\n" +
                "            \"showAverages\": true,\n" +
                "            \"helpDialog\": {\n" +
                "                \"title\": \"Динамика популярности\",\n" +
                "                \"content\": \"Числа обозначают уровень интереса к теме по отношению к наиболее высокому показателю в таблице для определенного региона и периода времени. 100 баллов означают наивысший уровень популярности запроса, 50 – уровень популярности запроса, вдвое меньший по сравнению с первым случаем. 0 баллов означает местоположение, по которому недостаточно данных о рассматриваемом запросе.\"\n" +
                "            },\n" +
                "            \"token\": \"APP6_UEAAAAAXfkQCALYo4U9ZeZYxU9AoV7cpgk4vOf9\",\n" +
                "            \"id\": \"TIMESERIES\",\n" +
                "            \"type\": \"fe_line_chart\",\n" +
                "            \"title\": \"Динамика популярности\",\n" +
                "            \"template\": \"fe\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": true,\n" +
                "            \"isCurated\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"request\": {\n" +
                "                \"geo\": {\n" +
                "                    \"country\": \"US\"\n" +
                "                },\n" +
                "                \"comparisonItem\": [\n" +
                "                    {\n" +
                "                        \"time\": \"2014-12-16 2019-12-16\",\n" +
                "                        \"complexKeywordsRestriction\": {\n" +
                "                            \"keyword\": [\n" +
                "                                {\n" +
                "                                    \"type\": \"BROAD\",\n" +
                "                                    \"value\": \"american eagle\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    },\n" +
                "                    {\n" +
                "                        \"time\": \"2014-12-16 2019-12-16\",\n" +
                "                        \"complexKeywordsRestriction\": {\n" +
                "                            \"keyword\": [\n" +
                "                                {\n" +
                "                                    \"type\": \"BROAD\",\n" +
                "                                    \"value\": \"aerie\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"resolution\": \"REGION\",\n" +
                "                \"locale\": \"ru\",\n" +
                "                \"requestOptions\": {\n" +
                "                    \"property\": \"\",\n" +
                "                    \"backend\": \"IZG\",\n" +
                "                    \"category\": 0\n" +
                "                },\n" +
                "                \"dataMode\": \"PERCENTAGES\"\n" +
                "            },\n" +
                "            \"geo\": \"US\",\n" +
                "            \"resolution\": \"provinces\",\n" +
                "            \"searchInterestLabel\": \"Уровень интереса к теме\",\n" +
                "            \"displayMode\": \"regions\",\n" +
                "            \"showLegend\": false,\n" +
                "            \"helpDialog\": {\n" +
                "                \"title\": \"Сравнение по субрегионам\",\n" +
                "                \"content\": \"Узнайте, насколько популярны те или иные запросы в различных регионах за определенный период времени. Используется стобалльная шкала, где 100 баллов означают регион с наивысшим уровнем популярности запроса, 50 – регион, уровень популярности запроса в котором вдвое ниже, чем в первом, 0 – регион, уровень популярности запроса в котором составляет не более 1% от уровня в первом регионе.\"\n" +
                "            },\n" +
                "            \"bullets\": [\n" +
                "                {\n" +
                "                    \"value\": \"american eagle\",\n" +
                "                    \"color\": \"PALETTE_COLOR_1\"\n" +
                "                },\n" +
                "                {\n" +
                "                    \"value\": \"aerie\",\n" +
                "                    \"color\": \"PALETTE_COLOR_2\"\n" +
                "                }\n" +
                "            ],\n" +
                "            \"token\": \"APP6_UEAAAAAXfkQCDjgYGg7qsGhyfdx_5QKFYoCyNiy\",\n" +
                "            \"id\": \"GEO_MAP\",\n" +
                "            \"type\": \"fe_multi_heat_map\",\n" +
                "            \"title\": \"Сравнение по субрегионам\",\n" +
                "            \"template\": \"fe\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": true,\n" +
                "            \"isCurated\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"text\": {\n" +
                "                \"text\": \"american eagle\"\n" +
                "            },\n" +
                "            \"id\": \"TITLE_0\",\n" +
                "            \"type\": \"fe_text\",\n" +
                "            \"title\": \"\",\n" +
                "            \"template\": \"fe_explore\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": true,\n" +
                "            \"isCurated\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"request\": {\n" +
                "                \"geo\": {\n" +
                "                    \"country\": \"US\"\n" +
                "                },\n" +
                "                \"comparisonItem\": [\n" +
                "                    {\n" +
                "                        \"time\": \"2014-12-16 2019-12-16\",\n" +
                "                        \"complexKeywordsRestriction\": {\n" +
                "                            \"keyword\": [\n" +
                "                                {\n" +
                "                                    \"type\": \"BROAD\",\n" +
                "                                    \"value\": \"american eagle\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"resolution\": \"REGION\",\n" +
                "                \"locale\": \"ru\",\n" +
                "                \"requestOptions\": {\n" +
                "                    \"property\": \"\",\n" +
                "                    \"backend\": \"IZG\",\n" +
                "                    \"category\": 0\n" +
                "                }\n" +
                "            },\n" +
                "            \"geo\": \"US\",\n" +
                "            \"resolution\": \"provinces\",\n" +
                "            \"searchInterestLabel\": \"Уровень интереса к теме\",\n" +
                "            \"displayMode\": \"regions\",\n" +
                "            \"helpDialog\": {\n" +
                "                \"title\": \"Популярность по субрегионам\",\n" +
                "                \"content\": \"Узнайте, где чаще всего выполнялся поиск по тому или иному запросу за определенный период. Запросам присваиваются баллы от 0 до 100, где 100 баллов означают местоположение с наибольшей долей популярности запроса, 50 баллов – местоположение, уровень популярности запроса в котором вдвое ниже, чем в первом. 0 баллов означает местоположение, по которому недостаточно данных о рассматриваемом запросе.<p><p> <b>Примечание.</b> Чем больше баллов, тем выше доля соответствующих запросов от всех запросов, а не их абсолютное количество. Поэтому маленькой стране, где запросы со словом \\\"банан\\\" составляют 80% от всех запросов, будет присвоено в два раза больше баллов, чем большой, где только 40% всех запросов содержат это слово.\",\n" +
                "                \"url\": \"https://support.google.com/trends/answer/4355212\"\n" +
                "            },\n" +
                "            \"color\": \"PALETTE_COLOR_1\",\n" +
                "            \"index\": 0,\n" +
                "            \"bullet\": \"american eagle\",\n" +
                "            \"token\": \"APP6_UEAAAAAXfkQCK5XDCtyPp58unkfq3zzhfuggSAc\",\n" +
                "            \"id\": \"GEO_MAP_0\",\n" +
                "            \"type\": \"fe_geo_chart_explore\",\n" +
                "            \"title\": \"Популярность по субрегионам\",\n" +
                "            \"template\": \"fe\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": false,\n" +
                "            \"isCurated\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"request\": {\n" +
                "                \"restriction\": {\n" +
                "                    \"geo\": {\n" +
                "                        \"country\": \"US\"\n" +
                "                    },\n" +
                "                    \"time\": \"2014-12-16 2019-12-16\",\n" +
                "                    \"originalTimeRangeForExploreUrl\": \"today 5-y\",\n" +
                "                    \"complexKeywordsRestriction\": {\n" +
                "                        \"keyword\": [\n" +
                "                            {\n" +
                "                                \"type\": \"BROAD\",\n" +
                "                                \"value\": \"american eagle\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                },\n" +
                "                \"keywordType\": \"QUERY\",\n" +
                "                \"metric\": [\n" +
                "                    \"TOP\",\n" +
                "                    \"RISING\"\n" +
                "                ],\n" +
                "                \"trendinessSettings\": {\n" +
                "                    \"compareTime\": \"2009-12-15 2014-12-15\"\n" +
                "                },\n" +
                "                \"requestOptions\": {\n" +
                "                    \"property\": \"\",\n" +
                "                    \"backend\": \"IZG\",\n" +
                "                    \"category\": 0\n" +
                "                },\n" +
                "                \"language\": \"ru\"\n" +
                "            },\n" +
                "            \"helpDialog\": {\n" +
                "                \"title\": \"Похожие запросы\",\n" +
                "                \"content\": \"Пользователи, которые выполняли поиск по вашему запросу, также искали информацию по темам, указанным ниже. <p>* <b>Топ</b> – наиболее популярные темы. Используется стобалльная шкала, где 100 – самые популярные темы, 50 – темы, которые ищут в два раза реже, чем самые популярные, и так далее. <p>* <b>В тренде</b> – запросы, количество запросов по которым наиболее заметно выросло с предыдущего периода времени. Надписью \\\"Сверхпопулярность\\\" отмечаются темы, количество запросов по которым возросло чрезвычайно сильно. Такое возможно в случае, если темы новые и поиск по ним ранее просто не выполнялся.\",\n" +
                "                \"url\": \"https://support.google.com/trends/answer/4355000\"\n" +
                "            },\n" +
                "            \"color\": \"PALETTE_COLOR_1\",\n" +
                "            \"keywordName\": \"american eagle\",\n" +
                "            \"token\": \"APP6_UEAAAAAXfkQCELIl45Qvqe-mtE5VXJsHosuLxAI\",\n" +
                "            \"id\": \"RELATED_QUERIES_0\",\n" +
                "            \"type\": \"fe_related_searches\",\n" +
                "            \"title\": \"Похожие запросы\",\n" +
                "            \"template\": \"fe\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": false,\n" +
                "            \"isCurated\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"text\": {\n" +
                "                \"text\": \"aerie\"\n" +
                "            },\n" +
                "            \"id\": \"TITLE_1\",\n" +
                "            \"type\": \"fe_text\",\n" +
                "            \"title\": \"\",\n" +
                "            \"template\": \"fe_explore\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": true,\n" +
                "            \"isCurated\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"request\": {\n" +
                "                \"geo\": {\n" +
                "                    \"country\": \"US\"\n" +
                "                },\n" +
                "                \"comparisonItem\": [\n" +
                "                    {\n" +
                "                        \"time\": \"2014-12-16 2019-12-16\",\n" +
                "                        \"complexKeywordsRestriction\": {\n" +
                "                            \"keyword\": [\n" +
                "                                {\n" +
                "                                    \"type\": \"BROAD\",\n" +
                "                                    \"value\": \"aerie\"\n" +
                "                                }\n" +
                "                            ]\n" +
                "                        }\n" +
                "                    }\n" +
                "                ],\n" +
                "                \"resolution\": \"REGION\",\n" +
                "                \"locale\": \"ru\",\n" +
                "                \"requestOptions\": {\n" +
                "                    \"property\": \"\",\n" +
                "                    \"backend\": \"IZG\",\n" +
                "                    \"category\": 0\n" +
                "                }\n" +
                "            },\n" +
                "            \"geo\": \"US\",\n" +
                "            \"resolution\": \"provinces\",\n" +
                "            \"searchInterestLabel\": \"Уровень интереса к теме\",\n" +
                "            \"displayMode\": \"regions\",\n" +
                "            \"helpDialog\": {\n" +
                "                \"title\": \"Популярность по субрегионам\",\n" +
                "                \"content\": \"Узнайте, где чаще всего выполнялся поиск по тому или иному запросу за определенный период. Запросам присваиваются баллы от 0 до 100, где 100 баллов означают местоположение с наибольшей долей популярности запроса, 50 баллов – местоположение, уровень популярности запроса в котором вдвое ниже, чем в первом. 0 баллов означает местоположение, по которому недостаточно данных о рассматриваемом запросе.<p><p> <b>Примечание.</b> Чем больше баллов, тем выше доля соответствующих запросов от всех запросов, а не их абсолютное количество. Поэтому маленькой стране, где запросы со словом \\\"банан\\\" составляют 80% от всех запросов, будет присвоено в два раза больше баллов, чем большой, где только 40% всех запросов содержат это слово.\",\n" +
                "                \"url\": \"https://support.google.com/trends/answer/4355212\"\n" +
                "            },\n" +
                "            \"color\": \"PALETTE_COLOR_2\",\n" +
                "            \"index\": 1,\n" +
                "            \"bullet\": \"aerie\",\n" +
                "            \"token\": \"APP6_UEAAAAAXfkQCPga965PT6ivdsN9lMY0uXk1rjfb\",\n" +
                "            \"id\": \"GEO_MAP_1\",\n" +
                "            \"type\": \"fe_geo_chart_explore\",\n" +
                "            \"title\": \"Популярность по субрегионам\",\n" +
                "            \"template\": \"fe\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": false,\n" +
                "            \"isCurated\": false\n" +
                "        },\n" +
                "        {\n" +
                "            \"request\": {\n" +
                "                \"restriction\": {\n" +
                "                    \"geo\": {\n" +
                "                        \"country\": \"US\"\n" +
                "                    },\n" +
                "                    \"time\": \"2014-12-16 2019-12-16\",\n" +
                "                    \"originalTimeRangeForExploreUrl\": \"today 5-y\",\n" +
                "                    \"complexKeywordsRestriction\": {\n" +
                "                        \"keyword\": [\n" +
                "                            {\n" +
                "                                \"type\": \"BROAD\",\n" +
                "                                \"value\": \"aerie\"\n" +
                "                            }\n" +
                "                        ]\n" +
                "                    }\n" +
                "                },\n" +
                "                \"keywordType\": \"QUERY\",\n" +
                "                \"metric\": [\n" +
                "                    \"TOP\",\n" +
                "                    \"RISING\"\n" +
                "                ],\n" +
                "                \"trendinessSettings\": {\n" +
                "                    \"compareTime\": \"2009-12-15 2014-12-15\"\n" +
                "                },\n" +
                "                \"requestOptions\": {\n" +
                "                    \"property\": \"\",\n" +
                "                    \"backend\": \"IZG\",\n" +
                "                    \"category\": 0\n" +
                "                },\n" +
                "                \"language\": \"ru\"\n" +
                "            },\n" +
                "            \"helpDialog\": {\n" +
                "                \"title\": \"Похожие запросы\",\n" +
                "                \"content\": \"Пользователи, которые выполняли поиск по вашему запросу, также искали информацию по темам, указанным ниже. <p>* <b>Топ</b> – наиболее популярные темы. Используется стобалльная шкала, где 100 – самые популярные темы, 50 – темы, которые ищут в два раза реже, чем самые популярные, и так далее. <p>* <b>В тренде</b> – запросы, количество запросов по которым наиболее заметно выросло с предыдущего периода времени. Надписью \\\"Сверхпопулярность\\\" отмечаются темы, количество запросов по которым возросло чрезвычайно сильно. Такое возможно в случае, если темы новые и поиск по ним ранее просто не выполнялся.\",\n" +
                "                \"url\": \"https://support.google.com/trends/answer/4355000\"\n" +
                "            },\n" +
                "            \"color\": \"PALETTE_COLOR_2\",\n" +
                "            \"keywordName\": \"aerie\",\n" +
                "            \"token\": \"APP6_UEAAAAAXfkQCP5S6ilBa-HFx8a6FrBjG4_5O78y\",\n" +
                "            \"id\": \"RELATED_QUERIES_1\",\n" +
                "            \"type\": \"fe_related_searches\",\n" +
                "            \"title\": \"Похожие запросы\",\n" +
                "            \"template\": \"fe\",\n" +
                "            \"embedTemplate\": \"fe_embed\",\n" +
                "            \"version\": \"1\",\n" +
                "            \"isLong\": false,\n" +
                "            \"isCurated\": false\n" +
                "        }\n" +
                "    ],\n" +
                "    \"keywords\": [\n" +
                "        {\n" +
                "            \"keyword\": \"american eagle\",\n" +
                "            \"name\": \"american eagle\",\n" +
                "            \"type\": \"Поисковый запрос\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"keyword\": \"aerie\",\n" +
                "            \"name\": \"aerie\",\n" +
                "            \"type\": \"Поисковый запрос\"\n" +
                "        }\n" +
                "    ],\n" +
                "    \"timeRanges\": [\n" +
                "        \"Последние 5 лет\",\n" +
                "        \"Последние 5 лет\"\n" +
                "    ],\n" +
                "    \"examples\": [],\n" +
                "    \"shareText\": \"Оцените интерес в Google Поиске к теме по ключевым словам \\\"american eagle, aerie\\\" в зависимости от времени или региона и сравните популярность запросов в Google Trends\",\n" +
                "    \"shouldShowMultiHeatMapMessage\": false\n" +
                "}";

        Gson g = new Gson();

        Wi3 request = g.fromJson(jsonString, Wi3.class);


        //for (Wi3 wi : request.response) {

        //    System.out.println(" token: " + wi.token);
        //    System.out.println(" id: " + wi.id);



        }
    }
//}