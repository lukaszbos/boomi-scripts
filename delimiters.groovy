import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil;

StringBuilder sb = new StringBuilder();

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);
    logger = ExecutionUtil.getBaseLogger();
    
    Scanner s = new Scanner(is).useDelimiter("\\A");
    String inputString = s.hasNext() ? s.next() : "";
    
    List<String> entries = Arrays.asList(inputString.split(System.lineSeparator()));
    
    entries.set(0, entries.get(0).substring(0, entries.get(0).lastIndexOf("|")));
    
    for(int j = 1 ; j < entries.size() ; j++){
        entries.set(j, entries.get(j).substring(entries.get(j).indexOf("|")));
        entries.set(j, entries.get(j).substring(0, entries.get(j).lastIndexOf("|")));
    }
    
    String processedString = String.join("", entries);
    processedString = processedString.replaceAll("\\|", "\\^");
    processedString = processedString.replaceFirst("\\^", "\\|");
    logger.info("processed:" + processedString);
    
    is = new ByteArrayInputStream(processedString.getBytes());
    dataContext.storeStream(is, props);
}
