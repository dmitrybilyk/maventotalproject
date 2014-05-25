//package com.learn.solr;
//
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.net.MalformedURLException;
//import java.net.URISyntaxException;
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.apache.solr.client.solrj.SolrQuery;
//import org.apache.solr.client.solrj.SolrServer;
//import org.apache.solr.client.solrj.SolrServerException;
//import org.apache.solr.client.solrj.impl.HttpSolrServer;
//import org.apache.solr.client.solrj.response.QueryResponse;
//import org.apache.solr.common.SolrDocument;
//import org.apache.solr.common.SolrDocumentList;
//import org.apache.solr.common.SolrInputDocument;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.JUnit4;
//
//
//@RunWith(JUnit4.class)
//public class TestSolr {
//
//    private SolrServer server;
//
//
//    /**
//     * setup.
//     */
//    @Before
//    public void setup() {
//        server = new HttpSolrServer("http://localhost:8080/solr/");
//        try {
//            server.deleteByQuery("*:*");
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Test Adding.
//     *
//     * @throws MalformedURLException error
//     */
//    @Test
//    public void testAdding() throws MalformedURLException {
//
//        try {
//
//            final SolrInputDocument doc1 = new SolrInputDocument();
//            doc1.addField("id", "id1", 1.0f);
//            doc1.addField("name", "doc1", 1.0f);
//            doc1.addField("price", 10);
//
//            final SolrInputDocument doc2 = new SolrInputDocument();
//            doc2.addField("id", "id2", 1.0f);
//            doc2.addField("name", "doc2", 1.0f);
//            doc2.addField("price", 20);
//
//            final Collection<solrinputdocument> docs = new ArrayList<solrinputdocument>();
//            docs.add(doc1);
//            docs.add(doc2);
//
//            server.add(docs);
//            server.commit();
//
//            final SolrQuery query = new SolrQuery();
//            query.setQuery("*:*");
//            query.addSortField("price", SolrQuery.ORDER.asc);
//            final QueryResponse rsp = server.query(query);
//
//            final SolrDocumentList solrDocumentList = rsp.getResults();
//
//            for (final SolrDocument doc : solrDocumentList) {
//                final String name = (String) doc.getFieldValue("name");
//                final String id = (String) doc.getFieldValue("id"); //id is the uniqueKey field
//                System.out.println("Name:" + name + " id:" + id);
//            }
//
//        } catch (SolrServerException e) {
//            e.printStackTrace();
//            Assert.fail(e.getMessage());
//        } catch (IOException e) {
//            e.printStackTrace();
//            Assert.fail(e.getMessage());
//        }
//    }
//
//}