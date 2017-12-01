package sometime.MinuteWriteFile1;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Traffic 오브잭트 값으로 매분당 특정 시간 만큼 3개의 값을 JSON 형태의 Text로 만들어서 특정 파일에 저장하는 로직.
 */

@Slf4j
@Component
public class FileWriteScheduler {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmm");

    @Value("${file.traffic.path}")
    private String writeFilePath;

    @Value("${file.traffic.name}")
    private String fileName;

    @Value("${file.traffic.extensions}")
    private String extensions;

    @Scheduled(cron="0 * * * * *")  // 매분
    public void writeFile() throws FileNotFoundException {
        log.info("The time is now {}", dateFormat.format(new Date()));

        List<Map<String, Object>> list = new ArrayList<>();

        // 매분동안 3초간 5개의 데이터를 만든다. 3, 6, 9, 12, 15
        getMinusSecondBySize(5).forEach(s -> {
            list.add(getNewData(s, "RocketMan"));
            list.add(getNewData(s, "TellMe"));
            list.add(getNewData(s, "NeverGiveUp"));
        });

        Gson gson = new Gson();
        String json = gson.toJson(list);

        Calendar cal = new GregorianCalendar(Locale.KOREA);
        cal.setTime(new Date()) ;
        cal.add(Calendar.MINUTE, -1);

        PrintWriter pw = new PrintWriter(writeFilePath + File.separator + fileName + "_" + dateFormat.format(cal.getTime()) + "." + extensions);
        pw.println(json);
        pw.close();
    }

    public Map<String, Object> getNewData(String d, String com) {
        Map<String, Object> t = new HashMap<>();
        t.put("datetime", d);
        t.put("bidRequests", (int) (Math.random() * 100));
        t.put("bidResponses", (int) (Math.random() * 100));
        t.put("winNotices", (int) (Math.random() * 100));
        t.put("clicks", (int) (Math.random() * 100));
        t.put("avgBidPrice", (int) (Math.random() * 100));
        t.put("avgWinPrice", (int) (Math.random() * 100));

        if(com.equals("RocketMan")) {
            t.put("dspInfoId", "12");
        } else if(com.equals("TellMe")) {
            t.put("dspInfoId", "13");
        } else if(com.equals("NeverGiveUp")) {
            t.put("dspInfoId", "14");
        }

        return t;
    }

    /**
     * 인자 s만큼 반복하면서 3초 기간의 데이터를 생성한다.
     * @param s
     * @return
     */
    public static List<String> getMinusSecondBySize(int s) {
        List<String> r = new ArrayList<>();
        Calendar cal = new GregorianCalendar(Locale.KOREA);
        cal.setTime(new Date()) ;
        cal.add(Calendar.MINUTE, -1);

        for(int i = 0; i <= s; i++) {
            SimpleDateFormat fm = new SimpleDateFormat("yyyyMMddHHmmss");
            r.add(fm.format(cal.getTime()));
            cal.add(Calendar.SECOND, 3);
        }

        return r;
    }
}
