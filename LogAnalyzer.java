/**
 * Read web server data and analyse
 * hourly access patterns.
 * 
 * @author Edward Pisco.
 * @version 03.15.16
 */
public class LogAnalyzer
{
    // Where to calculate the hourly access counts.
    private int[] hourCounts;
    // Use a LogfileReader to access the data.
    private LogfileReader reader;

    /**
     * Create an object to analyze hourly web accesses.
     */
    public LogAnalyzer(String fileName)
    { 
        // Create the array object to hold the hourly
        // access counts.
        hourCounts = new int[24];
        // Create the reader to obtain the data.
        reader = new LogfileReader(fileName);
    }

    /**
     * Analyze the hourly access data from the log file.
     */
    public void analyzeHourlyData()
    {
        while(reader.hasNext()) {
            LogEntry entry = reader.next();
            int hour = entry.getHour();
            hourCounts[hour]++;
        }
    }
    /**
     * Method that returns total number of accesses.
     */
    public int numberofAccesses()
    {
    int total = 0;
    for(int hour = 0; hour <= hourCounts.length -1; hour++)
    {
        total = total + hourCounts[hour];
    }
    return total;
    }
    /**
     * Method that returns the busiest hour.
     */
    public int busiestHour()
    {
       int maxCount = hourCounts[0];
       
       for(int hour = 1; hour <= hourCounts.length -1; hour++)
       {
           if(maxCount < hourCounts[hour])
           {
               maxCount = hourCounts[hour];
            }
        }
        return maxCount;
    }
    /**
     * Method that finds which two hour period is the busiest.
     */
    public int twobusiestHour()
    {
            int numBusiest = 0;
            int busiestHour = 0;
            
            for(int hour = 1; hour <= hourCounts.length -1; hour++)
            {
                if (numBusiest < hourCounts[hour] + hourCounts[hour + 1])
                {
                    busiestHour = hour;
                    numBusiest = hourCounts[hour] + hourCounts[hour + 1];
                    hour++;
                }
                else {
                    hour++;
                }
            }
            return busiestHour;
    }          
    /**
     * Method that returns the number of the least busy hour.
     */
    public int quietestHour()
    {
        int minCount = 0;
        boolean initializeCounter = false;
        for(int hour = 0; hour <= hourCounts.length -1; hour++)
        {
            if(initializeCounter == false)
            {
                if(hourCounts[hour] > 0)
                {
                    minCount = hourCounts[hour];
                    initializeCounter = true;
                }
           }
           else
            {
           if(hourCounts[hour] > 0 && minCount > hourCounts[hour])
                {
                    minCount = hourCounts[hour];
                }
            }
        }
        return minCount;
    }
    /**
     * Print the hourly counts.
     * These should have been set with a prior
     * call to analyzeHourlyData.
     */
    public void printHourlyCounts()
    {
        System.out.println("Hr: Count");
        for(int hour = 0; hour < hourCounts.length; hour++) {
            System.out.println(hour + ": " + hourCounts[hour]);
        }
    }
    
    /**
     * Print the lines of data read by the LogfileReader
     */
    public void printData()
    {
        reader.printData();
    }
}
