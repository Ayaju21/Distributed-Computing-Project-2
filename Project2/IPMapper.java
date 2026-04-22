
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import java.io.IOException;

public class IPMapper extends Mapper<Object, Text, Text, Text> {

    private Text userID = new Text();
    private Text ip = new Text();


@Override
protected void map(Object key, Text value, Context context) throws IOException, InterruptedException {
    // Each line contains: UserID IP_Address
    String line = value.toString().trim();
    String[] parts = line.split("\\s+");
    if (parts.length == 2) {
        userID.set(parts[0]);
        ip.set(parts[1]);
        context.write(userID, ip);      
  }
    }
}
