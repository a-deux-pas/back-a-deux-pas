package adeuxpas.back.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AdRepositoryTest {

    @InjectMocks
    AdRepository adRepository;

    @Test
    public void testFindByAcceptedStatusesFilteredOrderedByCreationDateDesc() {

    }
}
