import java.util.Properties;
import java.io.InputStream;
import com.boomi.execution.ExecutionUtil; 

 

for( int i = 0; i < dataContext.getDataCount(); i++ ) {
    InputStream is = dataContext.getStream(i);
    Properties props = dataContext.getProperties(i);

 

    //Value = ExecutionUtil.getDynamicProcessProperty("process_name");
    Value1 = "test";
    ExecutionUtil.setDynamicProcessProperty("process_name", Value1, false);

 

    dataContext.storeStream(is, props);
}
