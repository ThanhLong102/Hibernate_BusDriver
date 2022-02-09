package Repository;

import Entity.Line;

import java.util.List;

public interface LineDao {

    void addNewLine(Line line);

    List<Line> getAll();
}
