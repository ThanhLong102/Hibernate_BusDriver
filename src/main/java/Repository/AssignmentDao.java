package Repository;

import AssignmetTable.AssignmentTable;
import Entity.Assignment;

import java.util.List;

public interface AssignmentDao {

    void addNewAssgnment(Assignment assignment);

    List<AssignmentTable> getAll();

    void update(Assignment assignment);
}
