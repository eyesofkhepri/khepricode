package code1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 특정 객체를 갖는 List값에서 특정 다중 ID값으로 여러 데이터를 그룹화 하는 예제..
 */
public class GroupingExam1 {
    public static void main(String[] args) {

        List<DeptSales> result = Arrays.asList(
            new DeptSales("AOrga","ADept", 100000L, 2000L, 300L),
            new DeptSales("AOrga","ADept", 27000L, 560000L, 31000L),
            new DeptSales("AOrga","BDept", 6010000L, 20000L, 5000L),
            new DeptSales("AOrga","BDept", 130000L, 22000L, 343000L),
            new DeptSales("AOrga","CDept", 500010L, 52000L, 3000L),
            new DeptSales("AOrga","CDept", 100010L, 230000L, 350000L))
        .stream()
        .collect(Collectors.groupingBy(f -> f.getOrganization() + "_" + f.getDeptName()))
        .entrySet().stream()
        .map(g -> g.getValue().stream().reduce((a1, a2) -> {
            return new DeptSales(a1.getOrganization(), a2.getDeptName(), a1.getRevenue() + a2.getRevenue(), a1.getLoss() + a2.getLoss(), a1.getPurchase() + a2.getPurchase());
        })).map(m -> m.get()).collect(Collectors.toList());

        // [
        //   DeptSales(organization=AOrga, deptName=CDept, revenue=600020.0, loss=282000.0, purchase=353000.0),
        //   DeptSales(organization=AOrga, deptName=ADept, revenue=127000.0, loss=562000.0, purchase=31300.0),
        //   DeptSales(organization=AOrga, deptName=BDept, revenue=6140000.0, loss=42000.0, purchase=348000.0)
        // ]
        System.out.println(result);

    }
}
