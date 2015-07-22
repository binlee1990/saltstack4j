package saltstack4j.model.salt;

import com.alibaba.fastjson.annotation.JSONField;

public class Step {
    @JSONField(name = "__run_num__")
    private String runNum;
    private Changes changes;
    private String comment;
    private String name;
    private String result;

    public String getRunNum() {
        return runNum;
    }

    public void setRunNum(String runNum) {
        this.runNum = runNum;
    }

    public Changes getChanges() {
        return changes;
    }

    public void setChanges(Changes changes) {
        this.changes = changes;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
