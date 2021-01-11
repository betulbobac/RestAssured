package utils;
import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

public class ParseDynamicJson {

	public static void parseObject(JSONObject json,String key) throws JSONException {
		System.out.println(json.has(key));
		System.out.println(json.get(key));

	}

	
	public static void getKey(JSONObject json,String key) throws JSONException {
		boolean exist = json.has(key);
		Iterator<?> keys;
		String nextKeys = null;
		if(!exist) {
			keys = json.keys();
			while(keys.hasNext()) {
				nextKeys = (String)keys.next();
			}
			
			try {
				if(json.get(nextKeys) instanceof JSONObject) {
					if(exist == false) {
						getKey(json.getJSONObject(nextKeys),key);
					}
				}else if (json.get(nextKeys) instanceof JSONArray){
					
					JSONArray jsonArray = json.getJSONArray(nextKeys);
					for(int i=0;i<jsonArray.length();i++) {
						String jsonArrayString =jsonArray.get(i).toString();
						JSONObject innerJson = new JSONObject(jsonArrayString);
						
						if(exist == false) {
							getKey(innerJson,key);
						}
					}
				}
			}
			catch(Exception e) {
				//TODO: Handle exception
			}
		}else {
			parseObject(json, key);
		}
	}
	
	
	
	
	public static void main(String [] args) throws JSONException {
		/*
		 * String inputJson = "{\r\n" + "  id: 101,\r\n" + "  title: 'foo',\r\n" +
		 * "  body: 'bar',\r\n" + "  userId: 1\r\n" + "}";
		 */
		String inputJson = "[\r\n"
				+ "  {\r\n"
				+ "    \"postId\": 1,\r\n"
				+ "    \"id\": 1,\r\n"
				+ "    \"name\": \"id labore ex et quam laborum\",\r\n"
				+ "    \"email\": \"Eliseo@gardner.biz\",\r\n"
				+ "    \"body\": \"laudantium enim quasi est quidem magnam voluptate ipsam eos\\ntempora quo necessitatibus\\ndolor quam autem quasi\\nreiciendis et nam sapiente accusantium\"\r\n"
				+ "  },\r\n"
				+ "  {\r\n"
				+ "    \"postId\": 1,\r\n"
				+ "    \"id\": 2,\r\n"
				+ "    \"name\": \"quo vero reiciendis velit similique earum\",\r\n"
				+ "    \"email\": \"Jayne_Kuhic@sydney.com\",\r\n"
				+ "    \"body\": \"est natus enim nihil est dolore omnis voluptatem numquam\\net omnis occaecati quod ullam at\\nvoluptatem error expedita pariatur\\nnihil sint nostrum voluptatem reiciendis et\"\r\n"
				+ "  },\r\n"
				+ "  {\r\n"
				+ "    \"postId\": 1,\r\n"
				+ "    \"id\": 3,\r\n"
				+ "    \"name\": \"odio adipisci rerum aut animi\",\r\n"
				+ "    \"email\": \"Nikita@garfield.biz\",\r\n"
				+ "    \"body\": \"quia molestiae reprehenderit quasi aspernatur\\naut expedita occaecati aliquam eveniet laudantium\\nomnis quibusdam delectus saepe quia accusamus maiores nam est\\ncum et ducimus et vero voluptates excepturi deleniti ratione\"\r\n"
				+ "  },\r\n"
				+ "  {\r\n"
				+ "    \"postId\": 1,\r\n"
				+ "    \"id\": 4,\r\n"
				+ "    \"name\": \"alias odio sit\",\r\n"
				+ "    \"email\": \"Lew@alysha.tv\",\r\n"
				+ "    \"body\": \"non et atque\\noccaecati deserunt quas accusantium unde odit nobis qui voluptatem\\nquia voluptas consequuntur itaque dolor\\net qui rerum deleniti ut occaecati\"\r\n"
				+ "  },\r\n"
				+ "  {\r\n"
				+ "    \"postId\": 1,\r\n"
				+ "    \"id\": 5,\r\n"
				+ "    \"name\": \"vero eaque aliquid doloribus et culpa\",\r\n"
				+ "    \"email\": \"Hayden@althea.biz\",\r\n"
				+ "    \"body\": \"harum non quasi et ratione\\ntempore iure ex voluptates in ratione\\nharum architecto fugit inventore cupiditate\\nvoluptates magni quo et\"\r\n"
				+ "  }\r\n"
				+ "]";
		
		JSONObject inputJsonObject = new JSONObject(inputJson);
		getKey(inputJsonObject,"id");
		

	}
	

       
        
    

}
