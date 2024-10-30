@Transactional
    public void addValidationForTheMaximumSumInsuredValueOnBusinessClass(BusinessClassDTO businessClassDTOObject, RenewalSetupReq renewalSetupReqObject){
        if(Objects.nonNull(businessClassDTOObject.getMaxSi()) && Objects.nonNull(renewalSetupReqObject.getIevMaxAmount())){
            if (businessClassDTOObject.getMaxSi().compareTo(BigDecimal.valueOf(renewalSetupReqObject.getIevMaxAmount())) > 0){
                throw new ClientError(ErrorCode.BUSINESS_CLASS_CANNOT_BE_CREATED_DUE_TO_EXCEEDING_LIMIT_OF_MAXIMUM_SUM_INSURED);
            }
        }
    }
@Transactional
    public void addValidationForTheMaximumSumInsuredValueOnCreatingProducts(ProductDTO productDTOObject, RenewalSetupReq renewalSetupReqObject){
        if(Objects.nonNull(productDTOObject.getMaxSumInsured()) && Objects.nonNull(renewalSetupReqObject.getIevMaxAmount())){
            if(productDTOObject.getMaxSumInsured().compareTo(BigDecimal.valueOf(renewalSetupReqObject.getIevMaxAmount())) > 0){
                throw new ClientError(ErrorCode.PRODUCT_CANNOT_BE_CREATED_DUE_TO_EXCEEDING_LIMIT_OF_MAXIMUM_SUM_INSURED_VALUE);
            }
        }
    }

public void addValidationsForProductAndBusinessClassMaximumSumInsuredValues(BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject, Policy policyObject){
        BusinessClass businessClassObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.ofNullable(businessClassObject)
                .map(BusinessClass::getMaxSi)
                .filter(businessClassMaximumSumInsuredValue -> businessClassMaximumSumInsuredValue
                        .compareTo(policyObject.getSumInsured()) > 0)
                .ifPresent(exceededBusinessClassMaximumSumInsured ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_VALUE_IS_GREATER_THAN_POLICY_SUM_INSURED_VALUE);});
        Optional.ofNullable(productDetailsObject)
                .map(Product::getMaxSumInsured)
                .filter(productClassMaximumSumInsuredValue -> productClassMaximumSumInsuredValue
                        .compareTo(policyObject.getSumInsured()) > 0)
                .ifPresent(exceededValueOfProductClassMaximumSumInsured ->
                {throw new ClientError(ErrorCode.PRODUCT_DETAILS_MAXIMUM_SUM_INSURED_VALUE_IS_GREATER_THAN_POLICY_SUM_INSURED_VALUE);});
    }
public void addValidationsForTheSumInsuredOfPolicyToBusinessClassAndProductSumInsured(BusinessClassDTO businessClassDTOObject,ProductDTO productDTOObject,Policy policyObject){
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClass businessClasDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Optional.ofNullable(productClassDetailsObject)
                .map(Product::getMaxSumInsured)
                .filter(productMaximumSumInsuredValue -> productMaximumSumInsuredValue
                .compareTo(policyObject.getSumInsured()) < 0)
                .ifPresent(smallerValueOfSumInsured ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.ofNullable(businessClasDetailsObject)
                .map(BusinessClass::getMaxSi)
                .filter(businessClassMaximumSumInsuredValue -> businessClassMaximumSumInsuredValue
                .compareTo(policyObject.getSumInsured()) < 0)
                .ifPresent(smallerValueOfSumInsured ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicyToBusinessClassAndProductSumInsured(BusinessClassDTO businessClassDTOObject,ProductDTO productDTOObject,Policy policyObject){
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClass businessClasDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Optional.ofNullable(productClassDetailsObject)
                .map(Product::getMaxSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) < 0)
                .ifPresent(smallerValueOfSumInsured ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.ofNullable(businessClasDetailsObject)
                .map(BusinessClass::getMaxSi)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClasDetailsObject.getMaxSi()) < 0)
                .ifPresent(smallerValueOfSumInsured ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicyToBusinessClassAndProductSumInsured(BusinessClassDTO businessClassDTOObject,ProductDTO productDTOObject,Policy policyObject){
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClass businessClasDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Optional.ofNullable(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.ofNullable(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClasDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(BusinessClassDTO businessClassDTOObject,ProductDTO productDTOObject,Policy policyObject){
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClass businessClasDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Optional.ofNullable(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.ofNullable(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClasDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(BusinessClassDTO businessClassDTOObject,ProductDTO productDTOObject,Policy policyObject){
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClass businessClasDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Optional.ofNullable(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.ofNullable(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClasDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClassDTO businessClassDTOObject = (BusinessClassDTO) policyObject.getDepartment().getBusinessClasses();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClassDTO businessClassDTOObject = (BusinessClassDTO) policyObject.getDepartment().getBusinessClasses();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClassDTO businessClassDTOObject = (BusinessClassDTO) policyObject.getDepartment().getBusinessClasses();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject);
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject);
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject);
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject);
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject);
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject){
        String productDTOObject = policyObject.getProduct().getCode();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        BusinessClassDTO businessClassDTOObject = new BusinessClassDTO();
        ProductDTO productDtoObject = new ProductDTO();
        productDtoObject.setCode(policy.getProduct().getCode());
        businessClassDTOObject.setCode(policy.getProduct().getBusinessClass().getCode());
        renewalSetupService.addValidationsForTheSumInsuredOfPolicy(policy, businessClassDTOObject, productDtoObject);
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOObject = new ProductDTO();
        productDTOObject.setCode(policy.getProduct().getCode());
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        validForRenewal(policy, businessClassDtoObject, productDTOObject);
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredOfPolicy(Policy policyObject, BusinessClassDTO businessClassDTOObject, ProductDTO productDTOObject){
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDTOObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOObject.getCode());
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(productClassDetailsObject.getMaxSumInsured()) > 0)
                .ifPresent(exceededPolicySumInsuredVale ->
                {throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
        Optional.of(policyObject)
                .map(Policy::getSumInsured)
                .filter(policySumInsuredValue -> policySumInsuredValue
                .compareTo(businessClassDetailsObject.getMaxSi()) > 0)
                .ifPresent(exceededPolicySumInsuredValue ->
                {throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);});
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policyClassObject){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policyClassObject.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policyClassObject.getProduct().getCode());
        BigDecimal policySumInsuredValue = policyClassObject.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policy.getProduct().getCode());
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policy.getProduct().getCode());
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policy.getProduct().getCode());
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policy.getProduct().getCode());
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BusinessClassDTO businessClassDtoObject = new BusinessClassDTO();
        ProductDTO productDTOClassObject = new ProductDTO();
        businessClassDtoObject.setCode(policy.getProduct().getBusinessClass().getCode());
        productDTOClassObject.setCode(policy.getProduct().getCode());
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(businessClassDtoObject.getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(productDTOClassObject.getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BusinessClass businessClassDetailsObject = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(businessClassDetailsObject.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(policySumInsuredValue != null && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        Product productDetailsObject = policy.getProduct();
        if(Objects.isNull(productDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        BusinessClass businessClassDetailsObjects = businessClassService.getBusinessClassbyCode(policy.getProduct().getBusinessClass().getCode());
        if(Objects.isNull(businessClassDetailsObjects)){throw new ClientError(ErrorCode.BUSINESS_CLASS_NOT_FOUND);}
        Product productClassDetailsObject = productService.getProductByProductCode(policy.getProduct().getCode());
        if(Objects.isNull(productClassDetailsObject)){throw new ClientError(ErrorCode.PRODUCT_NOT_FOUND);}
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassDetailsObjects.getMaxSi()) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassDetailsObject.getMaxSumInsured()) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BigDecimal businessClassMaximumSumInsuredValue = policy.getProduct().getBusinessClass().getMaxSi();
        BigDecimal productClassMaximumSumInsuredValue = policy.getProduct().getMaxSumInsured();
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BigDecimal businessClassMaximumSumInsuredValue = policy.getProduct().getBusinessClass().getMaxSi();
        BigDecimal productClassMaximumSumInsuredValue = policy.getProduct().getMaxSumInsured();
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BigDecimal businessClassMaximumSumInsuredValue = policy.getProduct().getBusinessClass().getMaxSi();
        BigDecimal productClassMaximumSumInsuredValue = policy.getProduct().getMaxSumInsured();
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BigDecimal businessClassMaximumSumInsuredValue = policy.getProduct().getBusinessClass().getMaxSi();
        BigDecimal productClassMaximumSumInsuredValue = policy.getProduct().getMaxSumInsured();
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BigDecimal businessClassMaximumSumInsuredValue = policy.getProduct().getBusinessClass().getMaxSi();
        BigDecimal productClassMaximumSumInsuredValue = policy.getProduct().getMaxSumInsured();
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void addValidationsForTheSumInsuredValueOfPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        BigDecimal policySumInsuredValue = policy.getSumInsured();
        BigDecimal businessClassMaximumSumInsuredValue = policy.getProduct().getBusinessClass().getMaxSi();
        BigDecimal productClassMaximumSumInsuredValue = policy.getProduct().getMaxSumInsured();
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(businessClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.BUSINESS_CLASS_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
        if(Objects.nonNull(policySumInsuredValue) && policySumInsuredValue.compareTo(productClassMaximumSumInsuredValue) > 0){
            throw new ClientError(ErrorCode.PRODUCT_MAXIMUM_SUM_INSURED_IS_LESS_THAN_POLICY_SUM_INSURED);
        }
    }
public void validateDevelopmentOfficerAssociation(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        if(Objects.nonNull(policy.getAgents()) && !policy.getAgents().isEmpty()){
        for(PolicyAgent policyAgentObject : policy.getAgents()){
        Agent agentDetailsObject = policyAgentObject.getAgent();
        if(Objects.nonNull(agentDetailsObject) && Objects.nonNull(agentDetailsObject.getDevelopmentOfficer()))
        if(!agentDetailsObject.getDevelopmentOfficer().getValidForRenewal())
        throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
            }
        }
    }
public void validateDevelopmentOfficerAssociation(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        if(Objects.nonNull(policy.getAgents()) && !policy.getAgents().isEmpty()){
        for(PolicyAgent policyAgentObject : policy.getAgents()){
        Agent agentDetailsObject = policyAgentObject.getAgent();
        if(Objects.nonNull(agentDetailsObject) && Objects.nonNull(agentDetailsObject.getDevelopmentOfficer()))
        if(!agentDetailsObject.getDevelopmentOfficer().getValidForRenewal())
        throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
            }
        }
    }
public void validateDevelopmentOfficerAssociation(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        if(Objects.nonNull(policy.getAgents()) && !policy.getAgents().isEmpty()){
        for(PolicyAgent policyAgentObject : policy.getAgents()){
        Agent agentDetailsObject = policyAgentObject.getAgent();
        if(Objects.nonNull(agentDetailsObject) && Objects.nonNull(agentDetailsObject.getDevelopmentOfficer()))
        if(!agentDetailsObject.getDevelopmentOfficer().getValidForRenewal())
        throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
            }
        }
    }
public void validateDevelopmentOfficerAssociation(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        if(Objects.nonNull(policy.getAgents()) && !policy.getAgents().isEmpty()){
        for(PolicyAgent policyAgentObject : policy.getAgents()){
        Agent agentDetailsObject = policyAgentObject.getAgent();
        if(Objects.nonNull(agentDetailsObject) && Objects.nonNull(agentDetailsObject.getDevelopmentOfficer()))
        if(!agentDetailsObject.getDevelopmentOfficer().getValidForRenewal())
        throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
            }
        }
    }
public void validateDevelopmentOfficerAssociation(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        if(Objects.nonNull(policy.getAgents()) && !policy.getAgents().isEmpty()){
        for(PolicyAgent policyAgentObject : policy.getAgents()){
        Agent agentDetailsObject = policyAgentObject.getAgent();
        if(Objects.nonNull(agentDetailsObject) && Objects.nonNull(agentDetailsObject.getDevelopmentOfficer()))
        if(!agentDetailsObject.getDevelopmentOfficer().getValidForRenewal())
        throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
            }
        }
    }
public void validateDevelopmentOfficerAssociation(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        if(Objects.nonNull(policy.getAgents()) && !policy.getAgents().isEmpty()){
        for(PolicyAgent policyAgentObject : policy.getAgents()){
        Agent agentDetailsObject = policyAgentObject.getAgent();
        if(Objects.nonNull(agentDetailsObject) && Objects.nonNull(agentDetailsObject.getDevelopmentOfficer()))
        if(!agentDetailsObject.getDevelopmentOfficer().getValidForRenewal())
        throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
            }
        }
    }
public void validateDevelopmentOfficerAssociation(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        if(Objects.nonNull(policy.getAgents()) && !policy.getAgents().isEmpty()){
        for(PolicyAgent policyAgentObject : policy.getAgents()){
        Agent agentDetailsObject = policyAgentObject.getAgent();
        if(Objects.nonNull(agentDetailsObject) && Objects.nonNull(agentDetailsObject.getDevelopmentOfficer()))
        if(!agentDetailsObject.getDevelopmentOfficer().getValidForRenewal())
        throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
            for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
                    Agent agentObject = policyAgentDetailsObject.getAgent();
                if(Objects.nonNull(agentObject)){
                    DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                    if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    throw new ClientError(ErrorCode.DEVELOPMENT_OFFICER_DECLINED_FOR_POLICY_RENEW);
                }
            }
        }
    }
private Specification<PolicyRenewalView> getPolicyRenewalSpecification(PolicyRenewalSearch policyRenewalSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            LocalDateTime startExpiryDate = null, endExpiryDate = null;
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if(StringUtils.hasLength(policyRenewalSearch.getBranchCode()))
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.branchCode), policyRenewalSearch.getBranchCode()));
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyNumber())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_NUMBER), "%" + policyRenewalSearch.getPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_CODE), policyRenewalSearch.getClientCode() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getBusinessClassCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.BUSINESS_CLASS_CODE), policyRenewalSearch.getBusinessClassCode() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getBusinessType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.BUSINESS_TYPE), policyRenewalSearch.getBusinessType()));
            }
            if (Objects.nonNull(policyRenewalSearch.getDocumentType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.DOCUMENT_TYPE), policyRenewalSearch.getDocumentType()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientName())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_NAME), "%" + policyRenewalSearch.getClientName() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getTypeOfClt())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.TYPE_OF_CLT), policyRenewalSearch.getTypeOfClt()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getOldRefNo())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.OLD_REF_NO), "%" + policyRenewalSearch.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyType())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_TYPE), "%" + policyRenewalSearch.getPolicyType() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<PolicyRenewalView> getPolicyRenewalSpecification(PolicyRenewalSearch policyRenewalSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            LocalDateTime startExpiryDate = null, endExpiryDate = null;
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if(StringUtils.hasLength(policyRenewalSearch.getBranchCode()))
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.branchCode), policyRenewalSearch.getBranchCode()));
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyNumber())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_NUMBER), "%" + policyRenewalSearch.getPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_CODE), policyRenewalSearch.getClientCode() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getBusinessClassCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.BUSINESS_CLASS_CODE), policyRenewalSearch.getBusinessClassCode() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getBusinessType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.BUSINESS_TYPE), policyRenewalSearch.getBusinessType()));
            }
            if (Objects.nonNull(policyRenewalSearch.getDocumentType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.DOCUMENT_TYPE), policyRenewalSearch.getDocumentType()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientName())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_NAME), "%" + policyRenewalSearch.getClientName() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getTypeOfClt())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.TYPE_OF_CLT), policyRenewalSearch.getTypeOfClt()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getOldRefNo())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.OLD_REF_NO), "%" + policyRenewalSearch.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyType())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_TYPE), "%" + policyRenewalSearch.getPolicyType() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<PolicyRenewalView> getPolicyRenewalSpecification(PolicyRenewalSearch policyRenewalSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            LocalDateTime startExpiryDate = null, endExpiryDate = null;
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if(StringUtils.hasLength(policyRenewalSearch.getBranchCode()))
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.branchCode), policyRenewalSearch.getBranchCode()));
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyNumber())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_NUMBER), "%" + policyRenewalSearch.getPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_CODE), policyRenewalSearch.getClientCode() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getBusinessClassCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.BUSINESS_CLASS_CODE), policyRenewalSearch.getBusinessClassCode() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getBusinessType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.BUSINESS_TYPE), policyRenewalSearch.getBusinessType()));
            }
            if (Objects.nonNull(policyRenewalSearch.getDocumentType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.DOCUMENT_TYPE), policyRenewalSearch.getDocumentType()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientName())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_NAME), "%" + policyRenewalSearch.getClientName() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getTypeOfClt())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.TYPE_OF_CLT), policyRenewalSearch.getTypeOfClt()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getOldRefNo())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.OLD_REF_NO), "%" + policyRenewalSearch.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyType())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_TYPE), "%" + policyRenewalSearch.getPolicyType() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<PolicyRenewalView> getPolicyRenewalSpecification(PolicyRenewalSearch policyRenewalSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            LocalDateTime startExpiryDate = null, endExpiryDate = null;
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if(StringUtils.hasLength(policyRenewalSearch.getBranchCode()))
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.branchCode), policyRenewalSearch.getBranchCode()));
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyNumber())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_NUMBER), "%" + policyRenewalSearch.getPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_CODE), policyRenewalSearch.getClientCode() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getBusinessClassCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.BUSINESS_CLASS_CODE), policyRenewalSearch.getBusinessClassCode() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getBusinessType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.BUSINESS_TYPE), policyRenewalSearch.getBusinessType()));
            }
            if (Objects.nonNull(policyRenewalSearch.getDocumentType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.DOCUMENT_TYPE), policyRenewalSearch.getDocumentType()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientName())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_NAME), "%" + policyRenewalSearch.getClientName() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getTypeOfClt())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.TYPE_OF_CLT), policyRenewalSearch.getTypeOfClt()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getOldRefNo())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.OLD_REF_NO), "%" + policyRenewalSearch.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyType())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_TYPE), "%" + policyRenewalSearch.getPolicyType() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<PolicyRenewalView> getPolicyRenewalSpecification(PolicyRenewalSearch policyRenewalSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            LocalDateTime startExpiryDate = null, endExpiryDate = null;
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if(StringUtils.hasLength(policyRenewalSearch.getBranchCode()))
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.branchCode), policyRenewalSearch.getBranchCode()));
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyNumber())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_NUMBER), "%" + policyRenewalSearch.getPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_CODE), policyRenewalSearch.getClientCode() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getBusinessClassCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.BUSINESS_CLASS_CODE), policyRenewalSearch.getBusinessClassCode() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getBusinessType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.BUSINESS_TYPE), policyRenewalSearch.getBusinessType()));
            }
            if (Objects.nonNull(policyRenewalSearch.getDocumentType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.DOCUMENT_TYPE), policyRenewalSearch.getDocumentType()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientName())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_NAME), "%" + policyRenewalSearch.getClientName() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getTypeOfClt())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.TYPE_OF_CLT), policyRenewalSearch.getTypeOfClt()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getOldRefNo())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.OLD_REF_NO), "%" + policyRenewalSearch.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyType())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_TYPE), "%" + policyRenewalSearch.getPolicyType() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<PolicyRenewalView> getPolicyRenewalSpecification(PolicyRenewalSearch policyRenewalSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            LocalDateTime startExpiryDate = null, endExpiryDate = null;
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if(StringUtils.hasLength(policyRenewalSearch.getBranchCode()))
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.branchCode), policyRenewalSearch.getBranchCode()));
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyNumber())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_NUMBER), "%" + policyRenewalSearch.getPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_CODE), policyRenewalSearch.getClientCode() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getBusinessClassCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.BUSINESS_CLASS_CODE), policyRenewalSearch.getBusinessClassCode() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getBusinessType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.BUSINESS_TYPE), policyRenewalSearch.getBusinessType()));
            }
            if (Objects.nonNull(policyRenewalSearch.getDocumentType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.DOCUMENT_TYPE), policyRenewalSearch.getDocumentType()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientName())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_NAME), "%" + policyRenewalSearch.getClientName() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getTypeOfClt())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.TYPE_OF_CLT), policyRenewalSearch.getTypeOfClt()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getOldRefNo())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.OLD_REF_NO), "%" + policyRenewalSearch.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyType())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_TYPE), "%" + policyRenewalSearch.getPolicyType() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<PolicyRenewalView> getPolicyRenewalSpecification(PolicyRenewalSearch policyRenewalSearch) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            LocalDateTime startExpiryDate = null, endExpiryDate = null;
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) && policyRenewalSearch.getExpiryDateFrom().equals(policyRenewalSearch.getExpiryDateTo())) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MAX.truncatedTo(ChronoUnit.SECONDS));
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if(Objects.nonNull(policyRenewalSearch.getExpiryDateTo()) && Objects.nonNull(policyRenewalSearch.getExpiryDateFrom())){
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.between(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate, endExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateFrom()) ) {
                startExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateFrom(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), startExpiryDate));
            } else if (Objects.nonNull(policyRenewalSearch.getExpiryDateTo())) {
                endExpiryDate = LocalDateTime.of(policyRenewalSearch.getExpiryDateTo(), LocalTime.MIDNIGHT);
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get(PolicyRenewalView_.EXPIRY_DATE), endExpiryDate));
            }
            if(StringUtils.hasLength(policyRenewalSearch.getBranchCode()))
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.branchCode), policyRenewalSearch.getBranchCode()));
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyNumber())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_NUMBER), "%" + policyRenewalSearch.getPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_CODE), policyRenewalSearch.getClientCode() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getBusinessClassCode())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.BUSINESS_CLASS_CODE), policyRenewalSearch.getBusinessClassCode() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getBusinessType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.BUSINESS_TYPE), policyRenewalSearch.getBusinessType()));
            }
            if (Objects.nonNull(policyRenewalSearch.getDocumentType())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.DOCUMENT_TYPE), policyRenewalSearch.getDocumentType()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getClientName())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.CLIENT_NAME), "%" + policyRenewalSearch.getClientName() + "%"));
            }
            if (Objects.nonNull(policyRenewalSearch.getTypeOfClt())) {
                predicates.add(criteriaBuilder.equal(root.get(PolicyRenewalView_.TYPE_OF_CLT), policyRenewalSearch.getTypeOfClt()));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getOldRefNo())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.OLD_REF_NO), "%" + policyRenewalSearch.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policyRenewalSearch.getPolicyType())) {
                predicates.add(criteriaBuilder.like(root.get(PolicyRenewalView_.POLICY_TYPE), "%" + policyRenewalSearch.getPolicyType() + "%"));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
private Specification<Policy> getAllPolicyItemsSpecifications(PolicySearchParamsDTO policySearchParamsDTO) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (Objects.nonNull(policySearchParamsDTO.getPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER), "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.GROSS_PREMIUM).as(String.class), policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.NET_PREMIUM).as(String.class), policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getOldRefNo())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.OLD_REF_NO).as(String.class), policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getClientName())){
                Join<Policy, Client> getClientNameFromPolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getClientNameFromPolicy.get(Client_.CLIENT_NAME), "%" + policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getQuotationNumber())){
                Join<Policy, PolicyQuotation> getQuotationNNumberFromPolicyObject = root.join(Policy_.QUOTATION);
                predicates.add(criteriaBuilder.like(getQuotationNNumberFromPolicyObject.get(PolicyQuotation_.QUOTATION_NUMBER),
                        "%" + policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getIssueDate())){
                LocalDate issueDateOfPolicy = policySearchParamsDTO.getIssueDate();
                LocalDateTime startOfDay = issueDateOfPolicy.atStartOfDay();LocalDateTime endOfDay = issueDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.ISSUE_DATE), startOfDay, endOfDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getExpiryDate())){
                LocalDate expiryDateOfPolicy = policySearchParamsDTO.getExpiryDate();
                LocalDateTime startOfTheDay = expiryDateOfPolicy.atStartOfDay();LocalDateTime endOfTheDay = expiryDateOfPolicy.atTime(LocalTime.MAX);
                predicates.add(criteriaBuilder.between(root.get(Policy_.EXPIRY_DATE), startOfTheDay, endOfTheDay));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRetailPolicy())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RETAIL_POLICY), "%" + policySearchParamsDTO.getRetailPolicy() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCoverNoteNumber())){
                Join<Policy, CoverNote> getTheCoverNoteNumberFromPolicy = root.join(Policy_.COVER_NOTES);
                predicates.add(criteriaBuilder.like(getTheCoverNoteNumberFromPolicy.get(CoverNote_.COVER_NOTE_NUMBER),
                        "%" + policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getYear())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.YEAR).as(String.class), "%" + policySearchParamsDTO.getYear() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.PREMIUM_RATE).as(String.class), policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRecordId())){
                predicates.add(criteriaBuilder.equal(root.get(Policy_.RECORD_ID), "%" + policySearchParamsDTO.getRecordId()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getInsuranceType())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.INSURANCE_TYPE), "%" + policySearchParamsDTO.getInsuranceType() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getRenewalPolicyNumber())){
                predicates.add(criteriaBuilder.like(root.get(Policy_.RENEWAL_POLICY_NUMBER), "%" + policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPassportNumber())){
                Join<Policy, PolicyClient> getThePassportNumberFromPolicyClient = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePassportNumberFromPolicyClient.get(PolicyClient_.PASSPORT_NUMBER),
                        "%" + policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getProductName())){
                Join<Policy, Product> getTheProductNameFromThePolicy = root.join(Policy_.PRODUCT);
                predicates.add(criteriaBuilder.like(getTheProductNameFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + policySearchParamsDTO.getProductName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNtn())){
                Join<Policy, Client> getTheNtnNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNtnNumberFromThePolicy.get(Client_.NTN),
                        "%" + policySearchParamsDTO.getNtn() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getCnic())){
                Join<Policy, Client> getTheNicNumberFromThePolicy = root.join(Policy_.CLIENT);
                predicates.add(criteriaBuilder.like(getTheNicNumberFromThePolicy.get(Client_.CNIC),
                        "%" + policySearchParamsDTO.getCnic() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPhone())){
                Join<Policy, PolicyClient> getThePhoneNumberFromThePolicy = root.join(Policy_.POLICY_CLIENTS);
                predicates.add(criteriaBuilder.like(getThePhoneNumberFromThePolicy.get(PolicyClient_.PHONE_NUMBER),
                        "%" + policySearchParamsDTO.getPhone() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBranchCode())){
                Join<Policy, Branch> getTheBranchCodeFromThePolicy = root.join(Policy_.BRANCH);
                predicates.add(criteriaBuilder.like(getTheBranchCodeFromThePolicy.get(Branch_.CODE),
                        "%" + policySearchParamsDTO.getBranchCode() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                Join<Policy, Branch> getTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> getTheParticularDepartmentFromThePolicy = getTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> getTheBusinessClassFromThePolicy = getTheParticularDepartmentFromThePolicy.join(Department_.BUSINESS_CLASSES);
                predicates.add(criteriaBuilder.equal(getTheBusinessClassFromThePolicy.get(BusinessClass_.businessType), policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicySearchDTO> searchAllPolicyDetails(PolicySearchParamsDTO policySearchParamsDTO, int page, int size){
        Pageable pageableSortingObject = PageRequest.of(page, size, Sort.by("policyNumber").descending());
        Page<PolicySearchView> policySearchViewPageDetailsObject = policySearchRepository.findAll(getPolicySearchItemsSpecificationsDetails(policySearchParamsDTO), pageableSortingObject);
        return policySearchViewPageDetailsObject.map(policyService::policySearchToPolicySearchDTO);
    }
    private Specification<PolicySearchView> getPolicySearchItemsSpecificationsDetails(PolicySearchParamsDTO policySearchParamsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
            if (StringUtils.hasLength(policySearchParamsDTO.getPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyNumber),
                        "%" + policySearchParamsDTO.getPolicyNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getGrossPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.grossPremium).as(String.class),
                        policySearchParamsDTO.getGrossPremium() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getNetPremium())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.netPremium).as(String.class),
                        policySearchParamsDTO.getNetPremium() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getInsuranceType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.insuranceType).as(String.class),
                        policySearchParamsDTO.getInsuranceType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyRenewalType())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyRenewalType).as(String.class),
                        policySearchParamsDTO.getPolicyRenewalType() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCreatedBy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.createdBy).as(String.class),
                        policySearchParamsDTO.getCreatedBy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getBusinessClassDescription())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.businessClass).as(String.class),
                        policySearchParamsDTO.getBusinessClassDescription() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getClientName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.clientName).as(String.class),
                        policySearchParamsDTO.getClientName() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getMonth())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.month).as(String.class),
                        policySearchParamsDTO.getMonth() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPremiumRate())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.premiumRate).as(String.class),
                        policySearchParamsDTO.getPremiumRate() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyStatusCode())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyStatusCode).as(String.class),
                        policySearchParamsDTO.getPolicyStatusCode() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getMakeText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.makeText), "%"
                        + policySearchParamsDTO.getMakeText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getModelText())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.modelText), "%"
                        + policySearchParamsDTO.getModelText() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getManufactureYear())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.manufactureYear),
                        policySearchParamsDTO.getManufactureYear()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRegistrationNum())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.registrationNum),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getRegistrationNum().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEngineNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.engineNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getEngineNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getChassisNumber())) {
                Expression<String> replaceValue = criteriaBuilder.function("replace", String.class, root.get(PolicySearchView_.chassisNumber),
                        criteriaBuilder.literal("-"), criteriaBuilder.literal(""));
                predicatesListObject.add(criteriaBuilder.like(replaceValue, policySearchParamsDTO.getChassisNumber().replaceAll("-", "")));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCnic())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.cnic),
                        policySearchParamsDTO.getCnic() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getNtn())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.ntn),
                        policySearchParamsDTO.getNtn() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getEmail())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.email),
                        policySearchParamsDTO.getEmail() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getPassportNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.passportNumber),
                        policySearchParamsDTO.getPassportNumber() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getBusinessType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.BUSINESS_TYPE),
                        policySearchParamsDTO.getBusinessType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentType())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_TYPE),
                        policySearchParamsDTO.getDocumentType()));
            }
            if (Objects.nonNull(policySearchParamsDTO.getDocumentStatus())) {
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.DOCUMENT_STATUS),
                        policySearchParamsDTO.getDocumentStatus()));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getProductName())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.productName), "%" +
                        policySearchParamsDTO.getProductName() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getTypeOfPolicy())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyType), "%" +
                        policySearchParamsDTO.getTypeOfPolicy() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRenewalPolicyNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.renewalPolicyNumber), "%" +
                        policySearchParamsDTO.getRenewalPolicyNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getQuotationNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.quotationNumber), "%" +
                        policySearchParamsDTO.getQuotationNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getCoverNoteNumber())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.coverNoteNumber), "%" +
                        policySearchParamsDTO.getCoverNoteNumber() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getOldRefNo())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.oldRefNo), "%" +
                        policySearchParamsDTO.getOldRefNo() + "%"));
            }
            if (StringUtils.hasLength(policySearchParamsDTO.getRecordId())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.recordId), "%"
                        + policySearchParamsDTO.getRecordId() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicySource())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policySource).as(String.class),
                        policySearchParamsDTO.getPolicySource() + "%"));
            }
            if (Objects.nonNull(policySearchParamsDTO.getPolicyChannel())) {
                predicatesListObject.add(criteriaBuilder.like(root.get(PolicySearchView_.policyChannel).as(String.class),
                        policySearchParamsDTO.getPolicyChannel() + "%"));
            }
            if(Objects.nonNull(policySearchParamsDTO.getBusinessType())){
                predicatesListObject.add(criteriaBuilder.equal(root.get(PolicySearchView_.businessType),
                        policySearchParamsDTO.getBusinessType()));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public Page<PolicyDetailsDTO> searchAllPolicyDetails(PolicyDetailsDTO policyDetailsDTO, int page, int size){
        Pageable pageable = PageRequest.of(page, size, Sort.by("policyNumber"));
        Page<Policy> policySearchViewPageDetailsObject = policyRepository.findAll(getPolicySearchItemsSpecificationsDetails(policyDetailsDTO), pageable);
        return policySearchViewPageDetailsObject.map(policyService::policyDetailsToDto);
    }
    private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getBusinessType())){
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.equal(gettingTheBusinessClassesFromTheDepartments.get(BusinessClass_.businessType)
                        , "%" + businessTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if (Objects.nonNull(policyDetailsDTO.getBusinessType())) {
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.like(criteriaBuilder.lower(gettingTheBusinessClassesFromTheDepartments
                .get(String.valueOf(BusinessClass_.businessType))), "%" + businessTypeObject.name().toLowerCase() + "%"
                ));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
private Specification<Policy> getPolicySearchItemsSpecificationsDetails(PolicyDetailsDTO policyDetailsDTO){
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicatesListObject = new ArrayList<>();
                if(Objects.nonNull(policyDetailsDTO.getPolicyNumber())){
                    String policyNumberObject = policyDetailsDTO.getPolicyNumber();
                    predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.POLICY_NUMBER),
                            "%" + policyNumberObject + "%"));
                }
            if(Objects.nonNull(policyDetailsDTO.getRecordId())){
                String recordIdObject = policyDetailsDTO.getRecordId();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.RECORD_ID),
                        "%" + recordIdObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getPreviousPolicyNumber())){
                String previousPolicyNumberObject = policyDetailsDTO.getPreviousPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.PREVIOUS_POLICY_NUMBER),
                        "%" + previousPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getJgiPolicyNumber())){
                String jgiPolicyNumberObject = policyDetailsDTO.getJgiPolicyNumber();
                predicatesListObject.add(criteriaBuilder.like(root.get(Policy_.JGI_POLICY_NUMBER),
                        "%" + jgiPolicyNumberObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getTypeOfPolicy())){
                String policyTypeObject = policyDetailsDTO.getTypeOfPolicy();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.POLICY_TYPE),
                        "%" + policyTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getNetPremium())){
                BigDecimal netPremiumObject = policyDetailsDTO.getNetPremium();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.NET_PREMIUM), "%" + netPremiumObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getProductName())){
                String productNameObject = policyDetailsDTO.getProductName();
                Join<Policy, Product> gettingProductFromThePolicy = root.join(Policy_.PRODUCT);
                predicatesListObject.add(criteriaBuilder.like(gettingProductFromThePolicy.get(Product_.PRODUCT_NAME),
                        "%" + productNameObject + "%"));
            }
            if (Objects.nonNull(policyDetailsDTO.getBusinessType())) {
                BusinessType businessTypeObject = policyDetailsDTO.getBusinessType();
                Join<Policy, Branch> gettingTheBranchFromThePolicy = root.join(Policy_.BRANCH);
                Join<Branch, Department> gettingTheDepartmentFromTheBranch = gettingTheBranchFromThePolicy.join(Branch_.DEPARTMENTS);
                Join<Department, BusinessClass> gettingTheBusinessClassesFromTheDepartments = gettingTheDepartmentFromTheBranch.join(Department_.BUSINESS_CLASSES);
                predicatesListObject.add(criteriaBuilder.like(criteriaBuilder.lower(gettingTheBusinessClassesFromTheDepartments
                .get(String.valueOf(BusinessClass_.businessType))), "%" + businessTypeObject.name().toLowerCase() + "%"
                ));
            }
            if(Objects.nonNull(policyDetailsDTO.getPolicyDocumentStatus())){
                PolicyDocumentStatus policyDocumentStatusObject = policyDetailsDTO.getPolicyDocumentStatus();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DOCUMENT_STATUS), "%" + policyDocumentStatusObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getClientRemarks())){
                String clientRemarksObject = policyDetailsDTO.getClientRemarks();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.CLIENT_REMARKS), "%" + clientRemarksObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getDeductibleText())){
                String deductibleTextObject = policyDetailsDTO.getDeductibleText();
                predicatesListObject.add(criteriaBuilder.equal(root.get(Policy_.DEDUCTIBLE_TEXT).
                        as(String.class), "%" + deductibleTextObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleType())){
                String vehicleTypeObject = policyDetailsDTO.getVehicleType();
                Join<Policy, CorporateVehicleDetail> gettingTheCorporateVehicleObject = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheCorporateVehicleObject.get(CorporateVehicleDetail_.VEHICLE_TYPE)
                        , "%" + vehicleTypeObject + "%"));
            }
            if(Objects.nonNull(policyDetailsDTO.getVehicleMake())){
                String vehicleMakeObject = policyDetailsDTO.getVehicleMake();
                Join<Policy, CorporateVehicleDetail> gettingTheVehicleMakeFromCorporateVehicle = root.join(Policy_.CORPORATE_VEHICLE_DETAIL);
                predicatesListObject.add(criteriaBuilder.like(gettingTheVehicleMakeFromCorporateVehicle.get(CorporateVehicleDetail_.VEHICLE_MAKE)
                        , "%" + vehicleMakeObject + "%"));
            }
            return criteriaBuilder.and(predicatesListObject.toArray(new Predicate[predicatesListObject.size()]));
        };
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public List<BranchStaffDTO> getBranchesByCode(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        List<BranchStaffDTO> staffMemberBranchesObject = new ArrayList<>();
        Set<BranchStaff> setOfBranchStaffsObject = staffMemberDetailsObject.getBranches();
        if(Objects.nonNull(setOfBranchStaffsObject) && !setOfBranchStaffsObject.isEmpty()){
            List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff = getAllTheAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff = getAllTheDisAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            staffMemberBranchesObject.addAll(getAllTheAssociatedBranchesOfStaff);
            staffMemberBranchesObject.addAll(getAllTheDisAssociatedBranchesOfStaff);
        }
        return staffMemberBranchesObject;
    }
public List<BranchStaffDTO> getBranchesByCode(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        List<BranchStaffDTO> staffMemberBranchesObject = new ArrayList<>();
        Set<BranchStaff> setOfBranchStaffsObject = staffMemberDetailsObject.getBranches();
        if(Objects.nonNull(setOfBranchStaffsObject) && !setOfBranchStaffsObject.isEmpty()){
            List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff = getAllTheAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff = getAllTheDisAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            staffMemberBranchesObject.addAll(getAllTheAssociatedBranchesOfStaff);
            staffMemberBranchesObject.addAll(getAllTheDisAssociatedBranchesOfStaff);
        }
        return staffMemberBranchesObject;
    }
public List<BranchStaffDTO> getBranchesByCode(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        List<BranchStaffDTO> staffMemberBranchesObject = new ArrayList<>();
        Set<BranchStaff> setOfBranchStaffsObject = staffMemberDetailsObject.getBranches();
        if(Objects.nonNull(setOfBranchStaffsObject) && !setOfBranchStaffsObject.isEmpty()){
            List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff = getAllTheAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff = getAllTheDisAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            staffMemberBranchesObject.addAll(getAllTheAssociatedBranchesOfStaff);
            staffMemberBranchesObject.addAll(getAllTheDisAssociatedBranchesOfStaff);
        }
        return staffMemberBranchesObject;
    }
public List<BranchStaffDTO> getBranchesByCode(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        List<BranchStaffDTO> staffMemberBranchesObject = new ArrayList<>();
        Set<BranchStaff> setOfBranchStaffsObject = staffMemberDetailsObject.getBranches();
        if(Objects.nonNull(setOfBranchStaffsObject) && !setOfBranchStaffsObject.isEmpty()){
            List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff = getAllTheAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff = getAllTheDisAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            staffMemberBranchesObject.addAll(getAllTheAssociatedBranchesOfStaff);
            staffMemberBranchesObject.addAll(getAllTheDisAssociatedBranchesOfStaff);
        }
        return staffMemberBranchesObject;
    }
public List<BranchStaffDTO> getBranchesByCode(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        List<BranchStaffDTO> staffMemberBranchesObject = new ArrayList<>();
        Set<BranchStaff> setOfBranchStaffsObject = staffMemberDetailsObject.getBranches();
        if(Objects.nonNull(setOfBranchStaffsObject) && !setOfBranchStaffsObject.isEmpty()){
            List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff = getAllTheAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff = getAllTheDisAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            staffMemberBranchesObject.addAll(getAllTheAssociatedBranchesOfStaff);
            staffMemberBranchesObject.addAll(getAllTheDisAssociatedBranchesOfStaff);
        }
        return staffMemberBranchesObject;
    }
public List<BranchStaffDTO> getBranchesByCode(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        List<BranchStaffDTO> staffMemberBranchesObject = new ArrayList<>();
        Set<BranchStaff> setOfBranchStaffsObject = staffMemberDetailsObject.getBranches();
        if(Objects.nonNull(setOfBranchStaffsObject) && !setOfBranchStaffsObject.isEmpty()){
            List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff = getAllTheAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff = getAllTheDisAssociatedBranchesOfStaffs(setOfBranchStaffsObject);
            staffMemberBranchesObject.addAll(getAllTheAssociatedBranchesOfStaff);
            staffMemberBranchesObject.addAll(getAllTheDisAssociatedBranchesOfStaff);
        }
        return staffMemberBranchesObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
public PolicyDetailsDTO policySearchToPolicyDetailsDto(PolicySearchView policySearchViewObject){
        PolicyDetailsDTO policyDetailsDTOObject = new PolicyDetailsDTO();
        policyDetailsDTOObject.setPolicyNumber(policySearchViewObject.getPolicyNumber());
        policyDetailsDTOObject.setRecordId(policySearchViewObject.getRecordId());
        policyDetailsDTOObject.setNetPremium(policySearchViewObject.getNetPremium());
        policyDetailsDTOObject.setProductName(policySearchViewObject.getProductName());
        policyDetailsDTOObject.setTypeOfPolicy(policySearchViewObject.getPolicyType());
        policyDetailsDTOObject.setBusinessType(policySearchViewObject.getBusinessType());
        policyDetailsDTOObject.setPolicyStatus(policySearchViewObject.getPolicyStatusCode());
        policyDetailsDTOObject.setManufactureYear(policySearchViewObject.getManufactureYear());
        policyDetailsDTOObject.setRegistrationNumber(policySearchViewObject.getRegistrationNum());
        policyDetailsDTOObject.setDeductibleText(policySearchViewObject.getDeductibleText());
        policyDetailsDTOObject.setEngineNumber(policySearchViewObject.getEngineNumber());
        policyDetailsDTOObject.setChassisNumber(policySearchViewObject.getChassisNumber());
        policyDetailsDTOObject.setJgiPolicyNumber(policySearchViewObject.getJgiPolicyNumber());
        policyDetailsDTOObject.setPolicyDocumentStatus(policySearchViewObject.getDocumentStatus());
        policyDetailsDTOObject.setInsuredCareOf(policySearchViewObject.getInsuredCareOf());
        policyDetailsDTOObject.setPolicyChannel(policySearchViewObject.getPolicyChannel());
        policyDetailsDTOObject.setStartDate(policySearchViewObject.getStartDate());
        policyDetailsDTOObject.setGrossPremium(policySearchViewObject.getGrossPremium());
        policyDetailsDTOObject.setPremiumRate(policySearchViewObject.getPremiumRate());
        policyDetailsDTOObject.setNetPayable(policySearchViewObject.getNetPayable());
        policyDetailsDTOObject.setExpiryDate(policySearchViewObject.getExpiryDate());
        policyDetailsDTOObject.setCreatedOn(policySearchViewObject.getCreatedOn());
        policyDetailsDTOObject.setBranchCode(policySearchViewObject.getBranchCode());
        policyDetailsDTOObject.setCurrencyCode(policySearchViewObject.getCurrencyCode());
        policyDetailsDTOObject.setTotalSumInsured(policySearchViewObject.getSumInsured());
        policyDetailsDTOObject.setDocumentType(policySearchViewObject.getDocumentType());
        policyDetailsDTOObject.setQuotationNumber(policySearchViewObject.getQuotationNumber());
        policyDetailsDTOObject.setCoverNoteNumber(policySearchViewObject.getCoverNoteNumber());
        policyDetailsDTOObject.setRenewalPolicyNumber(policySearchViewObject.getRenewalPolicyNumber());
        policyDetailsDTOObject.setBusinessClass(policySearchViewObject.getBusinessClass());
        policyDetailsDTOObject.setThreeT(policySearchViewObject.getThreeT());
        return policyDetailsDTOObject;
    }
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setJgiPolicyNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getJgiPolicyNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyClientCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyClientCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDeductionCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDeductionCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCoverNoteNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCoverNoteNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setQuotationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getQuotationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrentEndorsementNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrentEndorsementNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setRegistrationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getRegistrationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setExtendedAmountFroPolicyCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getExtendedAmountFroPolicyCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModelText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModelText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModelCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModelCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleMakeText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleMakeText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleEngineNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleEngineNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleChassisNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleChassisNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTotalSumInsuredCodeAmount(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTotalSumInsuredCodeAmount());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setProductionChargesCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getProductionChargesCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTypeOfPolicy(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTypeOfPolicy());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrentQuotationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrentQuotationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPassportNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPassportNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionName(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionName());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrencyCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrencyCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyIdentityNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyIdentityNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionAccess(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionAccess());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientPhoneNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientPhoneNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModificationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModificationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionType(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionType());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setDeductibleText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getDeductibleText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientAddress(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientAddress());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCoverNoteAmendmentNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCoverNoteAmendmentNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyProductCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyProductCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTypeOfEndorsementPolicy(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTypeOfEndorsementPolicy());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentType(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentType());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentStatus(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentStatus());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassName(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassName());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassDescription(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassDescription());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyStatusCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyStatusCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setJgiPolicyNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getJgiPolicyNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyClientCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyClientCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDeductionCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDeductionCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCoverNoteNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCoverNoteNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setQuotationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getQuotationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrentEndorsementNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrentEndorsementNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setRegistrationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getRegistrationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setExtendedAmountFroPolicyCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getExtendedAmountFroPolicyCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModelText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModelText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModelCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModelCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleMakeText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleMakeText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleEngineNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleEngineNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleChassisNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleChassisNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTotalSumInsuredCodeAmount(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTotalSumInsuredCodeAmount());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setProductionChargesCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getProductionChargesCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTypeOfPolicy(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTypeOfPolicy());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrentQuotationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrentQuotationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPassportNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPassportNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionName(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionName());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrencyCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrencyCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyIdentityNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyIdentityNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionAccess(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionAccess());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientPhoneNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientPhoneNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModificationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModificationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionType(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionType());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setDeductibleText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getDeductibleText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientAddress(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientAddress());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCoverNoteAmendmentNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCoverNoteAmendmentNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyProductCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyProductCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTypeOfEndorsementPolicy(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTypeOfEndorsementPolicy());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentType(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentType());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentStatus(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentStatus());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassName(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassName());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassDescription(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassDescription());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyStatusCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyStatusCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setJgiPolicyNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getJgiPolicyNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyClientCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyClientCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDeductionCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDeductionCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCoverNoteNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCoverNoteNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setQuotationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getQuotationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrentEndorsementNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrentEndorsementNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setRegistrationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getRegistrationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setExtendedAmountFroPolicyCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getExtendedAmountFroPolicyCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModelText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModelText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModelCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModelCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleMakeText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleMakeText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleEngineNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleEngineNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleChassisNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleChassisNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTotalSumInsuredCodeAmount(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTotalSumInsuredCodeAmount());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setProductionChargesCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getProductionChargesCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTypeOfPolicy(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTypeOfPolicy());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrentQuotationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrentQuotationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPassportNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPassportNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionName(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionName());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCurrencyCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCurrencyCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyIdentityNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyIdentityNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionAccess(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionAccess());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientPhoneNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientPhoneNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setVehicleModificationNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getVehicleModificationNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setUserPermissionType(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getUserPermissionType());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setDeductibleText(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getDeductibleText());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientAddress(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientAddress());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setClientCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getClientCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setCoverNoteAmendmentNumber(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getCoverNoteAmendmentNumber());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyProductCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyProductCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setTypeOfEndorsementPolicy(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getTypeOfEndorsementPolicy());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentType(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentType());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyDocumentStatus(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyDocumentStatus());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassName(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassName());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassCode());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setBusinessClassDescription(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getBusinessClassDescription());
policyConductiveItemsRelatedItemsExchangeFromPolicyDto.setPolicyStatusCode(demonstrativeConditionsOfPolicyItemsDetailsFromCoInsurance.getPolicyStatusCode());
return policyConductiveItemsRelatedItemsExchangeFromPolicyDto;
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehiclePolicyItemsModel.getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehiclePolicyItemsModel.getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehiclePolicyItemsModel.getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehiclePolicyItemsModel.getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehiclePolicyItemsModel.getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehiclePolicyItemsModel.getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehiclePolicyItemsModel.getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehiclePolicyItemsModel.getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehiclePolicyItemsModel.getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehiclePolicyItemsModel.getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehiclePolicyItemsModel.getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehiclePolicyItemsModel.getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehiclePolicyItemsModel.getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehiclePolicyItemsModel.getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehiclePolicyItemsModel.getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehiclePolicyItemsModel.getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehiclePolicyItemsModel.getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehiclePolicyItemsModel.getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehiclePolicyItemsModel.getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehiclePolicyItemsModel.getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehiclePolicyItemsModel.getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehiclePolicyItemsModel.getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehiclePolicyItemsModel.getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehiclePolicyItemsModel.getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehiclePolicyItemsModel.getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehiclePolicyItemsModel.getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehiclePolicyItemsModel.getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehiclePolicyItemsModel.getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehiclePolicyItemsModel.getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehiclePolicyItemsModel.getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehiclePolicyItemsModel.getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehiclePolicyItemsModel.getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehiclePolicyItemsModel.getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehiclePolicyItemsModel.getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehiclePolicyItemsModel.getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehiclePolicyItemsModel.getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehiclePolicyItemsModel.getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehiclePolicyItemsModel.getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstaled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstaled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstaled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public void updateVehicleAccessories(VehicleAccessoryDTO vehicleAccessoryDTO, String accessoryName) {
VehicleAccessory vehicleAccessoriesItems = vehicleAccessoriesRepository.findByAccessoryName(accessoryName)
.orElseThrow(() -> new ResourceAccessException("ACCESSORIES NOT FOUND"));
VehicleModel vehiclePolicyItemsModel = vehicleAccessoriesItems.getPolicyItem().getVehicleModel();
MotorPolicyItem vehicleMotorPolicyItem = (MotorPolicyItem) vehicleAccessoriesItems.getPolicyItem().getAccessories();
Optional.ofNullable(vehicleAccessoriesItems.getId()).ifPresent(vehicleAccessoryDTO::setId);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryName()).ifPresent(vehicleAccessoryDTO::setAccessoryName);
Optional.ofNullable(vehicleAccessoriesItems.getAccessoryValue()).ifPresent(vehicleAccessoryDTO::setAccessoryValue);
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem()).map(MotorPolicyItem::getVehicleModel).flatMap(vehicleModel -> Optional.ofNullable(vehicleModel.getAllowedVModels())).
ifPresent(allowedModels -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setAllowedVModels(allowedModels));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getId()).ifPresent(vehicleModelId -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setId(vehicleModelId));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getVehicleType()).ifPresent(vehicleType -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setVehicleType(vehicleType));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelCode()).ifPresent(vehicleModelCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelCode(vehicleModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getModelText()).ifPresent(vehicleModelText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setModelText(vehicleModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeCode()).ifPresent(vehicleMakeCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeCode(vehicleMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getMakeText()).ifPresent(vehicleMakeText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setMakeText(vehicleMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeCode()).ifPresent(vehicleBodyCode -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeCode(vehicleBodyCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getBodyTypeText()).ifPresent(vehicleBodyText -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setBodyTypeText(vehicleBodyText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getEngineCapacity()).ifPresent(engineCapacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setEngineCapacity(engineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVehicleModel().getPassengerCapacity()).ifPresent(Capacity -> vehicleAccessoryDTO.getPolicyItem().getVehicleModel().setPassengerCapacity(Capacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAccessoriesSumInsured()).ifPresent(accessoriesInsured -> vehicleAccessoryDTO.getPolicyItem().setAccessoriesSumInsured(accessoriesInsured));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getAppliedForRegistration()).ifPresent(Registration -> vehicleAccessoryDTO.getPolicyItem().setAppliedForRegistration(Registration));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalled()).ifPresent(trackerInstalled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalled(trackerInstalled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getVrn()).ifPresent(vehicleItemVrnNumber -> vehicleAccessoryDTO.getPolicyItem().setVrn(vehicleItemVrnNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineNumber()).ifPresent(vehicleEngineNumber -> vehicleAccessoryDTO.getPolicyItem().setEngineNumber(vehicleEngineNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getChassisNumber()).ifPresent(vehicleChassisNumber -> vehicleAccessoryDTO.getPolicyItem().setChassisNumber(vehicleChassisNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getColorCode()).ifPresent(vehicleColorCode -> vehicleAccessoryDTO.getPolicyItem().setColorCode(vehicleColorCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getManufactureYear()).ifPresent(vehicleManufactureYear -> vehicleAccessoryDTO.getPolicyItem().setManufactureYear(vehicleManufactureYear));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getCoiTypeCode()).ifPresent(vehicleCoiTypeCode -> vehicleAccessoryDTO.getPolicyItem().setCoiTypeCode(vehicleCoiTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeCode()).ifPresent(vehicleBodyTypeCode -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeCode(vehicleBodyTypeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getBodyTypeText()).ifPresent(vehicleBodyTypeText -> vehicleAccessoryDTO.getPolicyItem().setBodyTypeText(vehicleBodyTypeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getEngineCapacity()).ifPresent(vehicleEngineCapacity -> vehicleAccessoryDTO.getPolicyItem().setEngineCapacity(vehicleEngineCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getPassengerCapacity()).ifPresent(vehiclePassengerCapacity -> vehicleAccessoryDTO.getPolicyItem().setPassengerCapacity(vehiclePassengerCapacity));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelCode()).ifPresent(vehicleItemModelCode -> vehicleAccessoryDTO.getPolicyItem().setModelCode(vehicleItemModelCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getModelText()).ifPresent(vehicleItemModelText -> vehicleAccessoryDTO.getPolicyItem().setModelText(vehicleItemModelText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeCode()).ifPresent(vehicleItemMakeCode -> vehicleAccessoryDTO.getPolicyItem().setMakeCode(vehicleItemMakeCode));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getMakeText()).ifPresent(vehicleItemMakeText -> vehicleAccessoryDTO.getPolicyItem().setMakeText(vehicleItemMakeText));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTracker()).ifPresent(vehicleItemsTracker -> vehicleAccessoryDTO.getPolicyItem().setTracker(vehicleItemsTracker));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTrackerInstalledType()).ifPresent(trackerTypeInstaled -> vehicleAccessoryDTO.getPolicyItem().setTrackerInstalledType(trackerTypeInstaled));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemEffectiveDate()).ifPresent(itemEffectiveDate -> vehicleAccessoryDTO.getPolicyItem().setItemEffectiveDate(itemEffectiveDate));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getItemSumInsuredStatus()).ifPresent(itemSumInsuredStatus -> vehicleAccessoryDTO.getPolicyItem().setItemSumInsuredStatus(itemSumInsuredStatus));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getSkipSurvey()).ifPresent(vehicleSkipSurvey -> vehicleAccessoryDTO.getPolicyItem().setSkipSurvey(vehicleSkipSurvey));
Optional.of(vehicleAccessoriesItems.getPolicyItem().getPolicy().isSelfSurvey()).ifPresent(vehicleSelfSurvey -> vehicleAccessoryDTO.getPolicyItem().setSelfSurvey(vehicleSelfSurvey));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getInsuredLicenseNumber()).ifPresent(insuredLicenseNumber -> vehicleAccessoryDTO.getPolicyItem().setInsuredLicenseNumber(insuredLicenseNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getLoanNumber()).ifPresent(vehicleLoanNumber -> vehicleAccessoryDTO.getPolicyItem().setLoanNumber(vehicleLoanNumber));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDamageRemarks()).ifPresent(vehicleDamageRemarks -> vehicleAccessoryDTO.getPolicyItem().setDamageRemarks(vehicleDamageRemarks));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getDiscounts()).ifPresent(vehicleItemDiscounts -> vehicleAccessoryDTO.getPolicyItem().setDiscounts(vehicleItemDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getTotalDiscounts()).ifPresent(vehicleTotalDiscounts -> vehicleAccessoryDTO.getPolicyItem().setTotalDiscounts(vehicleTotalDiscounts));
Optional.ofNullable(vehicleAccessoriesItems.getPolicyItem().getOtherInfoDetail()).ifPresent(vehicleOtherInfoDetail -> vehicleAccessoryDTO.getPolicyItem().setOtherInfoDetail(vehicleOtherInfoDetail));
if(vehicleMotorPolicyItem != null && vehicleMotorPolicyItem.getAccessories() != null){
List<VehicleAccessory> vehiclePolicyItemsAccessories = ((List<?>) vehicleMotorPolicyItem.getAccessories()).stream().filter(vehicleItem ->
vehicleItem instanceof VehicleAccessory).map(vehicleItem -> (VehicleAccessory) vehicleItem).collect(Collectors.toList());
vehicleMotorPolicyItem.setAccessories(vehiclePolicyItemsAccessories);}}
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchResponseToBranchStaffDto(BranchRes branchResDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        branchStaffDTOObject.setCode(branchResDetailsObject.getCode());
        branchStaffDTOObject.setActive(branchResDetailsObject.getActive());
        branchStaffDTOObject.setPhone(branchResDetailsObject.getPhone());
        branchStaffDTOObject.setStaffType(branchResDetailsObject.getStaffType());
        branchStaffDTOObject.setAddress(branchResDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchResDetailsObject.getEmail());
        branchStaffDTOObject.setSalesTax(branchResDetailsObject.getSalesTax());
        branchStaffDTOObject.setMemberName(branchResDetailsObject.getMemberName());
        branchStaffDTOObject.setBranchCode(branchResDetailsObject.getBranchCode());
        branchStaffDTOObject.setBranchName(branchResDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchResDetailsObject.getBranchType());
        branchStaffDTOObject.setCityName(branchResDetailsObject.getCityName());
        branchStaffDTOObject.setShortCode(branchResDetailsObject.getShortCode());
        branchStaffDTOObject.setCurrency(branchResDetailsObject.getCurrency());
        branchStaffDTOObject.setZoneCode(branchResDetailsObject.getZoneCode());
        branchStaffDTOObject.setZoneName(branchResDetailsObject.getZoneName());
        branchStaffDTOObject.setStampDuty(branchResDetailsObject.getStampDuty());
        branchStaffDTOObject.setAdvanceTax(branchResDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setClients(branchResDetailsObject.getClients());
        branchStaffDTOObject.setSettlementLimit(branchResDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setSurveyorLimit(branchResDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setFedInsuranceFee(branchResDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setSettlementApprover(branchResDetailsObject.getSettlementApprover());
        branchStaffDTOObject.setMaxSettlementRange(branchResDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setMinSettlementRange(branchResDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setLicenseNumber(branchResDetailsObject.getLicenseNumber());
        return branchStaffDTOObject;
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(BranchRes branchResObject){
        List<Branch> branchDetailsListObject = branchService.getBranchesWithCodes(branchResObject.getBranchCodes());
        branchDetailsListObject.forEach(branchesObject -> branchesObject.setActive(false));
        return branchService.saveAll(branchDetailsListObject).stream()
        .map(branchesObject -> branchService.branchResponseToBranchStaffDto(branchResObject))
        .collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(BranchRes branchResObject){
        List<Branch> branchDetailsListObject = branchService.getBranchesWithCodes(branchResObject.getBranchCodes());
        branchDetailsListObject.forEach(branchesObject -> branchesObject.setActive(false));
        return branchService.saveAll(branchDetailsListObject).stream()
        .map(branchesObject -> branchService.branchResponseToBranchStaffDto(branchResObject))
        .collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(BranchRes branchResObject){
        List<Branch> branchDetailsListObject = branchService.getBranchesWithCodes(branchResObject.getBranchCodes());
        branchDetailsListObject.forEach(branchesObject -> branchesObject.setActive(false));
        return branchService.saveAll(branchDetailsListObject).stream()
        .map(branchesObject -> branchService.branchResponseToBranchStaffDto(branchResObject))
        .collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
    public List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
    public List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
    public List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
    public List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
    public List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchStaffDTO> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
    public List<BranchStaffDTO> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchObjectToBranchStaffDto).collect(Collectors.toList());
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public BranchStaffDTO branchObjectToBranchStaffDto(Branch branchDetailsObject){
        BranchStaffDTO branchStaffDTOObject = new BranchStaffDTO();
        Set<BranchStaff> branchStaffDetailsSetObject = branchDetailsObject.getStaffMembers();
        BranchStaff branchStaffDetailsObject = branchStaffDetailsSetObject.iterator().next();
        branchStaffDTOObject.setCode(branchDetailsObject.getCode());
        branchStaffDTOObject.setAdvanceTax(branchDetailsObject.getAdvanceTax());
        branchStaffDTOObject.setStampDuty(branchDetailsObject.getStampDuty());
        branchStaffDTOObject.setAddress(branchDetailsObject.getAddress());
        branchStaffDTOObject.setEmail(branchDetailsObject.getEmail());
        branchStaffDTOObject.setPhone(branchDetailsObject.getPhone());
        branchStaffDTOObject.setQuoteSeq(branchDetailsObject.getQuoteSeq());
        branchStaffDTOObject.setSalesTax(branchDetailsObject.getSalesTax());
        branchStaffDTOObject.setShortCode(branchDetailsObject.getShortCode());
        branchStaffDTOObject.setPolicySeq(branchDetailsObject.getPolicySeq());
        branchStaffDTOObject.setBranchName(branchDetailsObject.getBranchName());
        branchStaffDTOObject.setBranchType(branchDetailsObject.getBranchType());
        branchStaffDTOObject.setFedInsuranceFee(branchDetailsObject.getFedInsuranceFee());
        branchStaffDTOObject.setCoverNoteSeq(branchDetailsObject.getCoverNoteSeq());
        branchStaffDTOObject.setMinSettlementRange(branchStaffDetailsObject.getMinSettlementRange());
        branchStaffDTOObject.setMaxSettlementRange(branchStaffDetailsObject.getMaxSettlementRange());
        branchStaffDTOObject.setSurveyorLimit(branchStaffDetailsObject.getSurveyorLimit());
        branchStaffDTOObject.setSettlementLimit(branchStaffDetailsObject.getSettlementLimit());
        branchStaffDTOObject.setRenewalNoticeSeq(branchDetailsObject.getRenewalNoticeSeq());
        branchStaffDTOObject.setSettlementApprover(branchStaffDetailsObject.getSettlementApprover());
        return branchStaffDTOObject;
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> getAllTheAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheDisAssociatedBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(true));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        branchListDetailsObject.forEach(branchDetailsObject -> branchDetailsObject.setActive(false));
        return branchService.saveAll(branchListDetailsObject).stream()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> branchStaffDetailsSetObject = staffMemberDetailsObject.getBranches();
        List<Branch> associatedBranchesListObject = branchStaffDetailsSetObject.stream()
        .filter(branchStaffObject -> Objects.nonNull(branchStaffObject.getBranch()) && branchStaffObject
        .getBranch().getActive()).map(BranchStaff::getBranch).collect(Collectors.toList());
        return associatedBranchesListObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String staffCode){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String code){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(code);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String code){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(code);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String code){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(code);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String code){
        StaffMember gettingTheStaffMemberDetailsObject = getStaffMemberByCode(code);
        Set<BranchStaff> gettingTheBranchStaffDetailsObject = gettingTheStaffMemberDetailsObject.getBranches();
        List<BranchStaffDTO> gettingTheBranchStaffDTOListObject = new ArrayList<>();
        Optional.ofNullable(gettingTheBranchStaffDetailsObject).orElse(Collections.emptySet()).stream()
                .map(this::toBranchStaff).forEach(gettingTheBranchStaffDTOListObject::add);
        return gettingTheBranchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        return Optional.ofNullable(staffMemberDetailsObject.getBranches())
        .orElse(Collections.emptySet()).stream().map(this::toBranchStaff).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        return Optional.ofNullable(staffMemberDetailsObject.getBranches())
        .orElse(Collections.emptySet()).stream().map(this::toBranchStaff).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        return Optional.ofNullable(staffMemberDetailsObject.getBranches())
        .orElse(Collections.emptySet()).stream().map(this::toBranchStaff).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheBranchesByCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        Set<BranchStaff> branchStaffDetailsSetObject = Optional.ofNullable(staffMemberDetailsObject.getBranches()).orElse(Collections.emptySet());
        return branchStaffDetailsSetObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> getAllTheAssociatedBranches(String staffCode){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(staffCode);
        Set<BranchStaff> getAssociatedBranchStaffsObject = staffMemberDetailsObject.getBranches();
        return getAssociatedBranchStaffsObject.stream().map(BranchStaff::getBranch).distinct()
        .map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        if(Objects.isNull(staffMemberDetailsObject)){return Collections.emptyList();}
        List<AgentBranchRes> getAllBranchesFromListObject = branchService.getAllBranches();
        List<BranchStaffDTO> branchStaffDTODetailsListObject = new ArrayList<>();
        for(AgentBranchRes branchDetailsObject: getAllBranchesFromListObject){
            boolean isAssociated = staffMemberDetailsObject.getBranches().stream().anyMatch(branchStaffObject ->
            branchStaffObject.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTODetailsListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTODetailsListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        Optional<StaffMember> staffMemberDetailsObject = staffMemberRepository.findByCode(code);
        if(staffMemberDetailsObject.isEmpty()){return Collections.emptyList();}
        StaffMember staffMemberObject = staffMemberDetailsObject.get();
        List<Branch> branchDetailsListObject = branchRepository.findAll();
        List<BranchRes> branchStaffDTOListObject = new ArrayList<>();
        for(Branch branchDetailsObject: branchDetailsListObject){
            boolean isAssociated = staffMemberObject.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branchDetailsObject));
            if(isAssociated){branchStaffDTOListObject.add(branchService.branchToBranchDto(branchDetailsObject));}
        }
        return branchStaffDTOListObject;
    }
    public List<BranchRes> AssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            BranchStaff branchStaffDetailsObject = new BranchStaff();
            branchStaffDetailsObject.setBranch(branchDetailsObject);
            branchStaffDetailsObject.setStaffMember(staffMemberDetailsObject);
            branchDetailsObject.getStaffMembers().add(branchStaffDetailsObject);
            staffMemberDetailsObject.getBranches().add(branchStaffDetailsObject);});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
    public List<BranchRes> DisAssociatingTheBranchesOfStaff(AssociateBranchesReq associateBranchesReqObject){
        List<Branch> branchListDetailsObject = branchService.getBranchesWithCodes(associateBranchesReqObject.getBranchCodes());
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(associateBranchesReqObject.getStaffCode());
        branchListDetailsObject.forEach(branchDetailsObject -> {
            staffMemberDetailsObject.getBranches().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));
            branchDetailsObject.getStaffMembers().removeIf(branchStaff -> branchStaff.getBranch()
            .equals(branchDetailsObject) && branchStaff.getStaffMember().equals(staffMemberDetailsObject));});
        branchService.saveAll(branchListDetailsObject);
        this.staffMemberRepository.save(staffMemberDetailsObject);
        return branchListDetailsObject.stream().map(branchService::branchToBranchDto).collect(Collectors.toList());
    }
public void AssociateBranches(String staffCode, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(staffCode);
        List<Branch> branchDetailsListObject = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode).collect(Collectors.toList());
        branchDetailsListObject.forEach(branchObject -> {
            if(!staffMemberRepository.existsByBranchAndStaffMember(branchObject, staffMemberDetailsObject)){
                BranchStaff branchStaffObject = new BranchStaff();
                branchStaffObject.setBranch(branchObject);
                branchStaffObject.setStaffMember(staffMemberDetailsObject);
                staffMemberRepository.save(branchStaffObject);
            }
        });
    }
public void AssociateBranches(String staffCode, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(staffCode);
        List<Branch> branchDetailsListObject = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode).collect(Collectors.toList());
        branchDetailsListObject.forEach(branchObject -> {
            if(!staffMemberRepository.existsByBranchAndStaffMember(branchObject, staffMemberDetailsObject)){
                BranchStaff branchStaffObject = new BranchStaff();
                branchStaffObject.setBranch(branchObject);
                branchStaffObject.setStaffMember(staffMemberDetailsObject);
                staffMemberRepository.save(branchStaffObject);
            }
        });
    }
public void AssociateBranches(String staffCode, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(staffCode);
        List<Branch> branchDetailsListObject = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode).collect(Collectors.toList());
        branchDetailsListObject.forEach(branchObject -> {
            if(!staffMemberRepository.existsByBranchAndStaffMember(branchObject, staffMemberDetailsObject)){
                BranchStaff branchStaffObject = new BranchStaff();
                branchStaffObject.setBranch(branchObject);
                branchStaffObject.setStaffMember(staffMemberDetailsObject);
                staffMemberRepository.save(branchStaffObject);
            }
        });
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream().map(staffMemberBranch ->
        mapper.map(staffMemberBranch.getBranch(), BranchRes.class)).collect(Collectors.toList());
    }
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream().map(staffMemberBranch ->
        mapper.map(staffMemberBranch.getBranch(), BranchRes.class)).collect(Collectors.toList());
    }
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream().map(staffMemberBranch ->
        mapper.map(staffMemberBranch.getBranch(), BranchRes.class)).collect(Collectors.toList());
    }
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream().map(staffMemberBranch ->
        mapper.map(staffMemberBranch.getBranch(), BranchRes.class)).collect(Collectors.toList());
    }
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream().map(staffMemberBranch ->
        mapper.map(staffMemberBranch.getBranch(), BranchRes.class)).collect(Collectors.toList());
    }
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream().map(staffMemberBranch ->
        mapper.map(staffMemberBranch.getBranch(), BranchRes.class)).collect(Collectors.toList());
    }
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
public List<BranchRes> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = this.getStaffMemberByCode(code);
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream().map(staffMemberBranch ->
        mapper.map(staffMemberBranch.getBranch(), BranchRes.class)).collect(Collectors.toList());
    }
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
@Transactional(readOnly = true)
    public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if (Objects.isNull(staffMemberDetailsObject)) {
            return Collections.emptyList();}
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream()
                .map(branchStaff -> mapper.map(branchStaff, BranchStaffDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    @Transactional
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
@Transactional(readOnly = true)
    public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if (Objects.isNull(staffMemberDetailsObject)) {
            return Collections.emptyList();}
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream()
                .map(branchStaff -> mapper.map(branchStaff, BranchStaffDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    @Transactional
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
@Transactional(readOnly = true)
    public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if (Objects.isNull(staffMemberDetailsObject)) {
            return Collections.emptyList();}
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream()
                .map(branchStaff -> mapper.map(branchStaff, BranchStaffDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    @Transactional
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
@Transactional(readOnly = true)
    public List<BranchStaffDTO> getAllTheAssociatedBranchesByStaffCode(String code){
        StaffMember staffMemberDetailsObject = getStaffMemberByCode(code);
        if (Objects.isNull(staffMemberDetailsObject)) {
            return Collections.emptyList();}
        Set<BranchStaff> branchStaffListObject = staffMemberDetailsObject.getBranches();
        return branchStaffListObject.stream()
                .map(branchStaff -> mapper.map(branchStaff, BranchStaffDTO.class))
                .collect(Collectors.toList());
    }
    @Transactional
    public void AssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream()
        .map(branchService::getBranchByBranchCode).filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            boolean associationExists = staffMember.getBranches().stream().anyMatch(branchStaff -> branchStaff.getBranch().equals(branch));
            if (!associationExists) {
                BranchStaff branchStaff = new BranchStaff();
                branchStaff.setBranch(branch);
                branchStaff.setStaffMember(staffMember);
                branch.getStaffMembers().add(branchStaff);
                staffMember.getBranches().add(branchStaff);
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
    @Transactional
    public void DisAssociateBranches(String code, AssociateBranchesReq associateBranchesReqObject){
        StaffMember staffMember = this.getStaffMemberByCode(code);
        if (Objects.isNull(staffMember)) {throw new ClientError(ErrorCode.STAFF_MEMBER_NOT_FOUND);}
        List<Branch> branchList = associateBranchesReqObject.getBranchCodes().stream().map(branchService::getBranchByBranchCode)
        .filter(Objects::nonNull).collect(Collectors.toList());
        branchList.forEach(branch -> {
            Optional<BranchStaff> associationToRemove = staffMember.getBranches().stream()
            .filter(branchStaff -> branchStaff.getBranch().equals(branch)).findFirst();
            if (associationToRemove.isPresent()) {
                staffMember.getBranches().remove(associationToRemove.get());
                branch.getStaffMembers().remove(associationToRemove.get());
                branchRepository.save(branch);
                staffMemberRepository.save(staffMember);
            }
        });
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
        for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
            Agent agentObject = policyAgentDetailsObject.getAgent();
            if(Objects.nonNull(agentObject)){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    renewalExceptionService.saveRenewalException(policy, ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);
                    throw new ClientError(ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);}
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
        for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
            Agent agentObject = policyAgentDetailsObject.getAgent();
            if(Objects.nonNull(agentObject)){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    renewalExceptionService.saveRenewalException(policy, ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);
                    throw new ClientError(ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);}
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
        for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
            Agent agentObject = policyAgentDetailsObject.getAgent();
            if(Objects.nonNull(agentObject)){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    renewalExceptionService.saveRenewalException(policy, ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);
                    throw new ClientError(ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);}
            }
        }
    }
public void validateDevelopmentOfficerAssociationForRenewPolicy(Policy policy){
        if(Objects.isNull(policy)){throw new ClientError(ErrorCode.POLICY_NOT_FOUND);}
        Set<PolicyAgent> policyAgentsObject = policy.getAgents();
        for(PolicyAgent policyAgentDetailsObject : policyAgentsObject){
            Agent agentObject = policyAgentDetailsObject.getAgent();
            if(Objects.nonNull(agentObject)){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject) && Boolean.FALSE.equals(developmentOfficerObject.getValidForRenewal())){
                    renewalExceptionService.saveRenewalException(policy, ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);
                    throw new ClientError(ErrorCode.DO_NOT_ACTIVE_FOR_POLICY_RENEWAL);}
            }
        }
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public StaffMemberDTO validationForDevOfficerAssociationToSingleBranch(String staffCode, String branchCode){
        StaffMember staffMember = getStaffMemberByCode(staffCode);
        if("D".equals(staffMember.getStaffType())){
            List<Long> branchesStaffListObject = getStaffMembersByBranchCode(branchCode);
            List<Agent> agentListObject = staffMemberRepository.findAllByIdInAndStaffType(branchesStaffListObject, StaffType.D.toString());
            List<DevelopmentOfficer> developmentOfficerListObject = new ArrayList<>();
            for(Agent agentObject: agentListObject){
                DevelopmentOfficer developmentOfficerObject = agentObject.getDevelopmentOfficer();
                if(Objects.nonNull(developmentOfficerObject)){developmentOfficerListObject.add(developmentOfficerObject);}
            }
            if(developmentOfficerListObject.size()>1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            StaffMemberDTO staffMemberDTOObject = new StaffMemberDTO();
            staffMemberDTOObject.setStaffType(staffMember.getStaffType());
            staffMemberDTOObject.setMemberName(staffMember.getMemberName());
            staffMemberDTOObject.setLicenseNumber(staffMember.getLicenseNumber());
            staffMemberDTOObject.setCnic(staffMember.getCnic());
            staffMemberDTOObject.setNtn(staffMember.getNtn());
            staffMemberDTOObject.setStn(staffMember.getStn());
            staffMemberDTOObject.setPhoneNumber(staffMember.getPhoneNumber());
            staffMemberDTOObject.setCommissionFlat(staffMember.getCommissionFlat());
            staffMemberDTOObject.setAgentEmpCode(staffMember.getAgentEmpCode());
            staffMemberDTOObject.setCityCode(staffMember.getCityCode());
            staffMemberDTOObject.setCityName(staffMember.getCityName());
            staffMemberDTOObject.setTaxRegionCode(staffMember.getTaxRegionCode());
            staffMemberDTOObject.setStaffAddress(staffMember.getStaffAddress());
            staffMemberDTOObject.setProvinceName(staffMember.getProvinceName());
            staffMemberDTOObject.setShortDescription(staffMember.getShortDescription());
            staffMemberDTOObject.setDevelopmentOfficersList(developmentOfficerListObject);
            return staffMemberDTOObject;
        } else {throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
public void validationForDevelopmentOfficerAssociationToSingleBranch(String staffCode, List<AssignCOBranchReq> assignCOBranchReqList) {
            StaffMember staffMember = getStaffMemberByCode(staffCode);
        if ("D".equals(staffMember.getStaffType()) && Objects.nonNull(staffMember.getStaffType())) {
            List<String> uniqueBranchCodes = assignCOBranchReqList.stream().map(AssignCOBranchReq::getBranchCode).collect(Collectors.toList());
            if(uniqueBranchCodes.size() > 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            List<BranchStaff> branchStaffListObject = branchStaffRepository.findAllByStaffMember(staffMember);
            List<Branch> branchStaffDetailsListObject =  branchStaffListObject.stream().map(BranchStaff::getBranch).collect(Collectors.toList());
            if(branchStaffDetailsListObject.size() >= 1){throw new ClientError(ErrorCode.DEV_OFFICER_SHOULD_BE_ASSOCIATED_WITH_ONLY_SINGLE_BRANCH);}
            else{throw new ClientError(ErrorCode.STAFF_TYPE_IS_OTHER_THAN_DO);}
        }
    }
