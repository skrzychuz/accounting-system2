package pl.coderstrust.soap;

import generatedFromXSD.OperationResult;
import org.springframework.stereotype.Service;

@Service
public class ResultAdapter {

  public OperationResult operationResultAdapter(pl.coderstrust.database.OperationResult result) {
    return result == pl.coderstrust.database.OperationResult.FAILURE ? OperationResult.FAILURE
        : OperationResult.SUCCESS;
  }
}
