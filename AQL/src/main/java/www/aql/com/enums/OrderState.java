package www.aql.com.enums;

/**
 * Created by Jason on 2016/7/27.
 */
public interface OrderState {
    int PREPARE_PAY = 1;
    int FINISHED_PAY = 2;
    int PREPARE_RECEIVE = 3;
    int FINISHED_RECEIVE = 4;
}
