package code1;

import lombok.Data;

/**
 * 부서별 수익 손해 비용과 관련된 모델
 */
@Data
public class DeptSales {
    // 조직명
    private String organization;

    // 부서명
    private String deptName;

    // 수익
    private double revenue;

    // 손해비용
    private double loss;

    // 구매비용
    private double purchase;

    public DeptSales() {
    }

    public DeptSales(String organization, String deptName, double revenue, double loss, double purchase) {
        this.organization = organization;
        this.deptName = deptName;
        this.revenue = revenue;
        this.loss = loss;
        this.purchase = purchase;
    }
}
