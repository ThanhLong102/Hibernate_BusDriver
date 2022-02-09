package Repository;

import AssignmetTable.AssignmentTable;
import AssignmetTable.LineTurn;
import Entity.Assignment;
import Util.DataUtil;
import Util.HibernateUtil;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class AssigmnetDaoImpl implements AssignmentDao{
    @Override
    public void addNewAssgnment(Assignment assignment) {
        if (DataUtil.isEmptyOrNull(assignment)) {
            return;
        }
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(assignment);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<AssignmentTable> getAll() {
        List<AssignmentTable> assignmentTableList = new ArrayList<>();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            List<Assignment> assignmentList = session.createQuery("from Assignment").list();
            for (Assignment assignment : assignmentList) {
                boolean checkExits = false;
                for (AssignmentTable assignmentTable : assignmentTableList) {
                    if (assignmentTable.getDriver().getId() == assignment.getDriver().getId()) {
                        assignmentTable.getLineTurns().add(new LineTurn(assignment.getLine(), assignment.getTurnNumber()));
                        checkExits = true;
                        break;
                    }
                }
                if (!checkExits) {
                    List<LineTurn> lineTurns = new ArrayList<>();
                    lineTurns.add(new LineTurn(assignment.getLine(), assignment.getTurnNumber()));
                    assignmentTableList.add(new AssignmentTable(assignment.getDriver(), lineTurns));
                }
            }

            return assignmentTableList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void update(Assignment assignment) {
        if (DataUtil.isEmptyOrNull(assignment)) {
            return;
        }
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(assignment);
            session.getTransaction().commit();
        } catch (Exception exception) {
            exception.printStackTrace();
            assert session != null;
            session.getTransaction().rollback();
        }
    }
}
