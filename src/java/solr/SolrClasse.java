package solr;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author manal
 */


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.impl.HttpSolrClient;
//import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;

public class SolrClasse {

    public HttpSolrClient getSolrClient(){
        final String solrUrl = "http://localhost:8983/solr";
return new HttpSolrClient.Builder(solrUrl)
    .withConnectionTimeout(10000)
    .withSocketTimeout(60000)
    .build();
    }
	public   SolrDocumentList connecter(String query) throws IOException, SolrServerException{
final SolrClient client = getSolrClient();

        final Map<String, String> queryParamMap = new HashMap<String, String>();
queryParamMap.put("q", query);
queryParamMap.put("fl", "id");
queryParamMap.put("sort", "id asc");
MapSolrParams queryParams = new MapSolrParams(queryParamMap);

final QueryResponse response = client.query("core0", queryParams);
final SolrDocumentList documents = response.getResults();
return documents;
	}
}

