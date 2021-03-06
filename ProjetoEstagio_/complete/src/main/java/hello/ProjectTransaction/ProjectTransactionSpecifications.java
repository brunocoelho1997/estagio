package hello.ProjectTransaction;

import hello.Client.Client;
import hello.EmployeeTransaction.EmployeeTransaction;
import hello.EntityPackage.Entity_;
import hello.Enums.Frequency;
import hello.Enums.Genre;
import hello.Project.Project;
import hello.SubType.SubType;
import hello.SubType.SubType_;
import hello.Transaction.Transaction;
import hello.Transaction.Transaction_;
import hello.Type.Type;
import hello.Type.Type_;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.time.LocalDate;

public class ProjectTransactionSpecifications {

    public static Specification<ProjectTransaction> filterByType(Type type) {
        return (root, query, cb) -> {
            Predicate predicateFinal = null;
            predicateFinal = cb.equal(root.get(Transaction_.type), type);
            return predicateFinal;
        };
    }

    public static Specification<ProjectTransaction> filterByNameType(String nameType) {
        return (root, query, cb) -> {
            Predicate predicateFinal = null;

            if(!nameType.isEmpty())
                predicateFinal = cb.equal(root.get(Transaction_.type).get(Type_.name), nameType);

            return predicateFinal;

        };
    }

    public static Specification<ProjectTransaction> filterDeleletedEntities(Boolean deletedEntities) {
        return (root, query, cb) -> {
            Predicate predicateFinal = null;

            predicateFinal = cb.equal(root.get(Entity_.actived), !(deletedEntities));

            return predicateFinal;

        };
    }

    public static Specification<ProjectTransaction> filterExecuted(Boolean executed) {
        return (root, query, cb) -> {
            Predicate predicateFinal = null;
//            query.orderBy(cb.desc(root.get(Transaction_.date)));
            predicateFinal = cb.equal(root.get(Transaction_.executed), executed);
            return predicateFinal;
        };
    }

    public static Specification<ProjectTransaction> filterGenre(Genre genre) {
        return (root, query, cb) -> {
            Predicate predicateFinal = null;
            predicateFinal = cb.equal(root.get(Transaction_.genre), genre);
            return predicateFinal;
        };
    }

    public static Specification<ProjectTransaction> filter(String value, String frequency, Project project, String dateSince, String dateUntil, String valueSince, String valueUntil) {
        return (root, query, cb) -> {


            Predicate predicateFinal = null;
            Predicate predicateName;
            Predicate predicateFrequency;
            Predicate predicateType;
            Predicate predicateSubType;
            Predicate predicateProject;
            Predicate predicateDateSince;
            Predicate predicateDateUntil;
            Predicate predicateValueSince;
            Predicate predicateValueUntil;
            Predicate predicateGenre;



            if(!value.isEmpty())
            {
                try{

                    Long id = Long.parseLong(value);
                    predicateFinal = cb.equal(root.get(Entity_.id), id);
                    return predicateFinal;

                }catch(NumberFormatException ex){

                    String name = value;
                    predicateName = cb.like(root.get(Transaction_.name), name);
                    predicateFinal = predicateName;
                }

            }

            if(!frequency.isEmpty())
            {
                predicateFrequency = cb.equal(root.get(Transaction_.frequency), Frequency.valueOf(frequency));
                if(predicateFinal!=null)
                    predicateFinal = cb.and(predicateFinal, predicateFrequency);
                else
                    predicateFinal = predicateFrequency;
            }

            if(project != null)
            {
                predicateProject = cb.equal(root.get(ProjectTransaction_.project), project);
                if(predicateFinal!=null)
                    predicateFinal = cb.and(predicateFinal, predicateProject);
                else
                    predicateFinal = predicateProject;
            }


            if(!dateSince.isEmpty())
            {
                LocalDate localDateSince = LocalDate.parse(dateSince);
                predicateDateSince = cb.greaterThan(root.get(Transaction_.date), localDateSince);

                if(predicateFinal!=null)
                    predicateFinal = cb.and(predicateFinal, predicateDateSince);
                else
                    predicateFinal = predicateDateSince;
            }

            if(!dateUntil.isEmpty())
            {
                LocalDate localDateUntil = LocalDate.parse(dateUntil);
                predicateDateUntil = cb.lessThan(root.get(Transaction_.date), localDateUntil);

                if(predicateFinal!=null)
                    predicateFinal = cb.and(predicateFinal, predicateDateUntil);
                else
                    predicateFinal = predicateDateUntil;
            }

            if(!valueSince.isEmpty())
            {
                Float valueFloat;

                try{
                    valueFloat = Float.parseFloat(valueSince);

                    predicateValueSince = cb.greaterThan(root.get(Transaction_.value), valueFloat);

                    if(predicateFinal!=null)
                        predicateFinal = cb.and(predicateFinal, predicateValueSince);
                    else
                        predicateFinal = predicateValueSince;

                }catch (Exception ex){
                    ; //ignore
                }
            }

            if(!valueUntil.isEmpty())
            {
                Float valueFloat;

                try{
                    valueFloat = Float.parseFloat(valueUntil);

                    predicateValueUntil = cb.lessThan(root.get(Transaction_.value), valueFloat);

                    if(predicateFinal!=null)
                        predicateFinal = cb.and(predicateFinal, predicateValueUntil);
                    else
                        predicateFinal = predicateValueUntil;

                }catch (Exception ex){
                    ; //ignore
                }
            }

            return predicateFinal;

        };
    }
}
