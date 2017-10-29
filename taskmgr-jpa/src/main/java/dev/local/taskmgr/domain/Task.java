package dev.local.taskmgr.domain;

import lombok.*;
import lombok.experimental.Wither;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "task")
@Builder
@AllArgsConstructor
@ToString(exclude={"taskList", "owner", "participants", "histories"})
public class Task implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id @Getter @Setter @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Wither @Getter @Setter
    private String desc;

    @Wither @Getter @Setter
    private boolean completed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_list_id")
    @Getter @Setter @Wither
    private TaskList taskList;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    @Getter @Setter @Wither
    private Profile owner;

    @ManyToMany(mappedBy = "tasksJoined")
    @Getter @Setter @Wither
    private List<Profile> participants;

    @Temporal(TemporalType.DATE)
    @Wither @Getter @Setter
    private Date dueDate;

    @Temporal(TemporalType.DATE)
    @Wither @Getter @Setter
    private Date reminder;

    @Wither @Getter @Setter
    private int priority;

    @Wither @Getter @Setter
    private String remark;

    @Wither @Getter @Setter
    private List<TaskHistory> histories = new ArrayList<>();

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof Task
                && id != null
                && id.equals(((Task) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}