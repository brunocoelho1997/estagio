package hello.Type;

import hello.EmployeeTransaction.EmployeeTransaction;
import hello.SubType.SubType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long>, JpaSpecificationExecutor {

    Type findById(long id);

//    List<Type> findByNameAndSubTypeName(String name, String subTypeName);

    /*
    TODO: list?? Caso tenha mais do que um com o mesmo nome.... Como resolver?
    o mesmo devia acontecer para findByNameAndSubTypeName.
    Como resolver???
     */
//    List<Type> findByNameAndSubTypeNull(String name);


    List<Type>findByName(String name);
    Page<Type> findAllByActivedAndManuallyCreated(Pageable pageable, boolean actived, boolean manuallyCreated);

    List<Type> findAllByActived(boolean actived);

    List<Type> findAllByActivedAndManuallyCreated(boolean actived, boolean manuallyCreated);


    //lixo
    List<Type> findByManuallyCreated(boolean manuallyCreated);
    List<Type> findByManuallyCreatedAndSubTypeListContaining(boolean manuallyCreated, SubType subType);

}
