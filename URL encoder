import java.util.Properties;
import java.io.InputStream;
import java.net.URLEncoder;
import com.boomi.execution.ExecutionUtil;

String stringToEncode = ''
String encodedString = ''

for( int i = 0; i < dataContext.getDataCount(); i++ ) 
{
InputStream is = dataContext.getStream(i);
Properties props = dataContext.getProperties(i);

Scanner s = new Scanner(is).useDelimiter("\\A");
String inputString = s.hasNext() ? s.next() : "";

encodedString = URLEncoder.encode(inputString, "UTF-8").replace("+", "%20")

is = new ByteArrayInputStream(encodedString.getBytes());
dataContext.storeStream(is, props);
}
