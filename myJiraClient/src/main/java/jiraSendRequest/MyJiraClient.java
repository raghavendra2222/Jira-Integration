package jiraSendRequest;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;

public class MyJiraClient {

public static String createJiraIssue(String PN,String id,String url,String Username,String Password,String description){
		Client client = Client.create();
		client.addFilter(new HTTPBasicAuthFilter(Username, Password));
		WebResource webResource = client.resource(url);	
		String input="{\"fields\":{\"project\":{\"key\":\"" + PN + "\"},\"summary\":\"Eclipse Demo Ticket\",\"assignee\":{\"accountId\":\""+id+"\"},\"description\":\""+description+ "\", \"issuetype\":{\"name\":\"Bug\"},\"priority\":{\"name\":\"High\"}}}";
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class, input);
		String output = response.getEntity(String.class);
		System.out.println("JIRA Server returns:\n" + output);
		return output;
	}
public static String getJiraDetails(String IssueId,String url,String Username,String Password) {
	Client client = Client.create();
	String url1 = url+IssueId;
	client.addFilter(new HTTPBasicAuthFilter(Username, Password));
	WebResource webResource = client.resource(url1);
	ClientResponse response = webResource.accept("application/json").get(ClientResponse.class);
	String output = response.getEntity(String.class);
	System.out.println("Getting Response"+output);
	return output;
}
public static void deleteDetails(String IssueKey,String url,String Username,String Password) {
	Client client = Client.create();
	String url1 = url+IssueKey;
	client.addFilter(new HTTPBasicAuthFilter(Username, Password));
	WebResource webResource = client.resource(url1);
	ClientResponse response = webResource.type("application/json").accept("application/json").delete(ClientResponse.class);
	System.out.println("Successfully deleted");
}
public static String updateJiraIssue(String Username,String Password,String url,String IssueKey,String id,String weblink) {
	Client client = Client.create();
	String url1 = url+IssueKey;
	client.addFilter(new HTTPBasicAuthFilter(Username, Password));
	WebResource webResource = client.resource(url1);
	//String updateInput = "{\"fields\":{\"assignee\":{\"accountId\":\""+id+"\"}}}";
	String updateInput = "{\"object\":{\"url\":\""+weblink+"\",\"title\":\"created using eclipse\"}}";
	ClientResponse response = webResource.type("application/json").accept("application/json").put(ClientResponse.class, updateInput);
	String output = response.getEntity(String.class);
	System.out.println("Getting Updated Response"+output);
	return output;
}
public static void main(String args[]) {
	String jiraProjectName = "A2E7F4";
	String host = new String("https://naruto-uzamaki.atlassian.net"); 
	String url = host + "/rest/api/2/issue";
	String Username = "raghavendrajagirdar20@gmail.com";
	String Password = "YqPdOJvhwhfAYHpITeZAEABC";
	String id="62553e90cdc24000704a9305";
	String IssueId = "/10006";
	String description = "We can give the description from eclipse";
	String weblink = "https://developer.atlassian.com/cloud/jira/platform/rest/v3/api-group-issue-remote-links/#api-rest-api-3-issue-issueidorkey-remotelink-get";
	String IssueKey = "/A2E7F4-11/remotelink";	
	//createJiraIssue(jiraProjectName,id,url,Username,Password,description,weblink);
	getJiraDetails(IssueId,url,Username,Password);
	//deleteDetails(IssueKey, url, Username, Password);
	//updateJiraIssue(Username, Password, url, IssueKey,id,weblink);
}
}