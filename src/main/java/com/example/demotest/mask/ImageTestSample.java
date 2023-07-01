package com.example.demotest.mask;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.AcsResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.RpcAcsRequest;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.facebody.model.v20191230.DetectMaskRequest;
import com.aliyuncs.facebody.model.v20191230.DetectMaskResponse;
import com.aliyuncs.profile.DefaultProfile;

public class ImageTestSample {
    static IAcsClient client = null;
    public static void main(String[] args) throws Exception {
        DefaultProfile profile = DefaultProfile.getProfile(
                "cn-shanghai",             //默认
                "LTAI4FkRZVs9QBCYjFFgvWs7",         //您的Access Key ID
                "VcqmkRAz7yayvF8RhsM4jkUaITOd17");    //您的Access Key Secret
        client = new DefaultAcsClient(profile);
        // 图像增强
//        testMakeSuperResolutionImage(); //超分辨率
//        testChangeImageSize(); //尺寸变换
//        testExtendImageStyle(); //风格迁移
//        testRecolorImage(); //色彩迁移
//        // 图像识别
//        testDetectImageElements(); //元素检测
//        testRecognizeImageColor(); //色板识别
//        testRecognizeImageStyle(); //风格识别
//        testRecognizeScene(); //场景识别
//        testTaggingImage(); //通用图像打标
//        // 人脸人体
//        testDetectFace(); //人脸检测
//        testRecognizeFace(); //人脸属性识别
//        testCompareFace(); //人脸比对
        testDetectMask(); //人脸口罩检测
        //图像裁剪
//        testSegmentCommonImage(); // 图片裁剪
//        testSegmentBody(); //人像分割
//        testSegmentCommodity(); //商品分割
//        // 目标检测
//        testDetectMainBody(); //主体检测
//        testDetectVehicle(); //机动车检测
//        // 内容审核
//        testScanImage(); // 内容审核
//        // 商品理解
//        testClassifyCommodity(); // 商品分类
//        // 文字识别
//        testRecognizeLicensePlate(); // 车牌识别
//        testRecognizeBankCard(); //银行卡识别
//        testRecognizeIdentityCard(); // 身份证识别
//        testRecognizeTable(); //表格识别
//        testRecognizeTrainTicket(); //火车票识别
//        testRecognizeDriverLicense(); //驾驶证识别
//        testRecognizeBusinessCard(); //名片识别
//        testRecognizeTaxiInvoice(); //出租车发票识别
//        testRecognizeVINCode(); //VIN码识别
//        testRecognizeStamp(); //公章识别
//        testRecognizeCharacter(); //通用识别
//        testRecognizeBusinessLicense(); //营业执照识别
//        testRecognizeAccountPage(); //户口页识别
//        testRecognizeDrivingLicense(); //行驶证识别
        System.out.println("--------------------------------------------------------------");
    }
    private static <R extends RpcAcsRequest<T>, T extends AcsResponse> T getAcsResponse(R req) throws Exception {
        try {
            return client.getAcsResponse(req);
        } catch (ServerException e) {
            // 服务端异常
            System.out.println(String.format("ServerException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (ClientException e) {
            // 客户端错误
            System.out.println(String.format("ClientException: errCode=%s, errMsg=%s", e.getErrCode(), e.getErrMsg()));
            throw e;
        } catch (Exception e) {
            System.out.println("Exception:" + e.getMessage());
            throw e;
        }
    }
//    public static void testChangeImageSize() throws Exception {
//        System.out.println("--------  尺寸变换 --------------");
//        ChangeImageSizeRequest req = new ChangeImageSizeRequest();
//        req.setUrl(
//                "https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/ChangeImageSize/change-image-size-src.png");
//        req.setWidth(800);
//        req.setHeight(600);
//        ChangeImageSizeResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testExtendImageStyle() throws Exception {
//        ExtendImageStyleRequest req = new ExtendImageStyleRequest();
//        System.out.println("--------  图像风格迁移 --------------");
//        req.setStyleUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/ExtendImageStyle/majorUrl.jpeg");
//        req.setMajorUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/ExtendImageStyle/styleUrl.jpeg");
//        ExtendImageStyleResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testMakeSuperResolutionImage() throws Exception {
//        MakeSuperResolutionImageRequest req = new MakeSuperResolutionImageRequest();
//        System.out.print("--------  清晰化/超分辨 ----");
//        req.setUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/MakeSuperResolution/sup-dog.png");
//        MakeSuperResolutionImageResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testRecognizeImageColor() throws Exception {
//        RecognizeImageColorRequest req = new RecognizeImageColorRequest();
//        System.out.print("--------  色板识别 ----");
//        req.setUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecognizeImageColor/RecognizeImageColor.png");
//        RecognizeImageColorResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testRecognizeImageStyle() throws Exception {
//        RecognizeImageStyleRequest req = new RecognizeImageStyleRequest();
//        System.out.print("--------  风格识别 ----");
//        req.setUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecognizeImageStyle/technology.png");
//        RecognizeImageStyleResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testRecolorImage() throws Exception {
//        RecolorImageRequest req = new RecolorImageRequest();
//        System.out.print("--------  拓色 ----");
//        req.setUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecolorImage/recolor-1-src.png");
//        req.setColorCount(3);
//        //(选填)拓色模式,默认AUTO,取值范围[AUTO:自动拓色, TEMPLATE:色板拓色, REF_PIC:参考图拓色]
//        req.setMode("AUTO");
//        RecolorImageResponse resp = null;
//        //AUTO
//        System.out.println("自动拓色");
//        resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//        System.out.println("参考图拓色");
//        req.setMode("REF_PIC");
//        req.setRefUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecolorImage/recolor-refurl-src.png");
//        resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//        System.out.println("色板拓色");
//        req.setMode("TEMPLATE");
//        req.setUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecolorImage/recolor-refurl-src.png");
//        List<ColorTemplate> colorTemplateList = new ArrayList<>();
//        colorTemplateList.add(buildColorTemplate("056A6B"));
//        colorTemplateList.add(buildColorTemplate("FF0000"));
//        colorTemplateList.add(buildColorTemplate("00FF00"));
//        req.setColorTemplates(colorTemplateList);
//        resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static ColorTemplate buildColorTemplate(String color) {
//        ColorTemplate colorTemplate = new ColorTemplate();
//        colorTemplate.setColor(color);
//        return colorTemplate;
//    }
//    public static void testDetectImageElements() throws Exception {
//        DetectImageElementsRequest req = new DetectImageElementsRequest();
//        System.out.print("--------  元素检测 -----");
//        req.setUrl(
//                "http://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/DetectImageElements/detect-elements-src.png");
//        DetectImageElementsResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testDetectFace() throws Exception {
//        System.out.println("--------  人脸检测定位 --------------");
//        DetectFaceRequest req = new DetectFaceRequest();
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/SegmentCommonImage/segmentimage-src-hu.jpeg");
//        DetectFaceResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
    public static void testDetectMask() throws Exception {
        System.out.println("--------  人脸口罩检测定位 --------------");
        DetectMaskRequest req = new DetectMaskRequest();
        req.setImageURL("https://vasm-qa-public.obs.cn-east-2.myhwclouds.com/15e05fb034d649fabcfa27f54f16ec8e.jpg");
        DetectMaskResponse resp = getAcsResponse(req);
        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
    }
//    public static void testDetectMainBody() throws Exception {
//        System.out.println("--------  主体检测 --------------");
//        DetectMainBodyRequest req = new DetectMainBodyRequest();
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/SegmentCommonImage/segmentimage-src-hu.jpeg");
//        DetectMainBodyResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testClassifyCommodity() throws Exception {
//        System.out.println("--------  商品分类 --------------");
//        ClassifyCommodityRequest req = new ClassifyCommodityRequest();
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/SegmentCommonImage/segmentimage-src-hu.jpeg");
//        ClassifyCommodityResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testSegmentCommonImage() throws Exception {
//        System.out.println("--------  图像裁剪 --------------");
//        SegmentCommonImageRequest req = new SegmentCommonImageRequest();
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs"
//                + ".com/viapi-demo/images/SegmentCommonImage/segmengImage.png");
//        SegmentCommonImageResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testSegmentBody() throws Exception {
//        System.out.println("--------  人像分割 --------------");
//        SegmentBodyRequest req = new SegmentBodyRequest();
//        req.setImageURL(
//                "https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/SegmentBody/%E4%BA%BA%E5%83%8F%E5%88%86%E5%89%B2.png");
//        SegmentBodyResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //商品分割
//    public static void testSegmentCommodity() throws Exception {
//        System.out.println("--------  商品分割 --------------");
//        SegmentCommodityRequest req = new SegmentCommodityRequest();
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/SegmentCommodity/%E5%95%86%E5%93%81.png");
//        SegmentCommodityResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testRecognizeLicensePlate() throws Exception {
//        System.out.println("--------  车牌识别 --------------");
//        RecognizeLicensePlateRequest req = new RecognizeLicensePlateRequest();
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecognizeLicensePlate/licensePlate.jpg");
//        RecognizeLicensePlateResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testRecognizeBankCard() throws Exception {
//        System.out.println("--------  银行卡识别 --------------");
//        RecognizeBankCardRequest req = new RecognizeBankCardRequest();
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecognizeBankCard/bankcard.jpg");
//        RecognizeBankCardResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testRecognizeIdentityCard() throws Exception {
//        System.out.println("--------  身份证识别 --------------");
//        RecognizeIdentityCardRequest req = new RecognizeIdentityCardRequest();
//        req.setSide("face");
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/RecognizeIdentityCard/identityCard.jpg");
//        RecognizeIdentityCardResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    public static void testScanImage() throws Exception {
//        System.out.println("--------  内容审核 --------------");
//        ScanImageRequest req = new ScanImageRequest();
//        List<String> scenes = new ArrayList<String>();
//        scenes.add("porn");
//        req.setScenes(scenes);
//        List<Task> tasks = new ArrayList<Task>();
//        com.aliyuncs.imageaudit.model.v20191230.ScanImageRequest.Task task = new Task();
//        task.setDataId(UUID.randomUUID().toString());
//        task.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/images/ChangeImageSize/change-image-size-src.png");
//        tasks.add(task);
//        req.setTasks(tasks);
//        ScanImageResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //场景识别
//    public static void testRecognizeScene() throws Exception {
//        System.out.println("--------  场景识别 --------------");
//        RecognizeSceneRequest req = new RecognizeSceneRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeSceneResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //表格识别
//    public static void testRecognizeTable() throws Exception {
//        System.out.println("--------  表格识别 --------------");
//        RecognizeTableRequest req = new RecognizeTableRequest();
//        req.setUseFinanceModel(false);
//        req.setAssureDirection(false);
//        req.setHasLine(false);
//        req.setSkipDetection(false);
//        req.setOutputFormat("json");
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeTableResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //通用图像打标
//    public static void testTaggingImage() throws Exception {
//        System.out.println("--------  通用图像打标 --------------");
//        TaggingImageRequest req = new TaggingImageRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        TaggingImageResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //机动车检测
//    public static void testDetectVehicle() throws Exception {
//        System.out.println("--------  机动车检测 --------------");
//        DetectVehicleRequest req = new DetectVehicleRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        DetectVehicleResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //人脸属性识别
//    public static void testRecognizeFace() throws Exception {
//        System.out.println("--------  人脸属性识别 --------------");
//        RecognizeFaceRequest req = new RecognizeFaceRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeFaceResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //火车票识别
//    public static void testRecognizeTrainTicket() throws Exception {
//        System.out.println("--------  火车票识别 --------------");
//        RecognizeTrainTicketRequest req = new RecognizeTrainTicketRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeTrainTicketResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //驾驶证识别
//    public static void testRecognizeDriverLicense() throws Exception {
//        System.out.println("--------  驾驶证识别 --------------");
//        RecognizeDriverLicenseRequest req = new RecognizeDriverLicenseRequest();
//        req.setSide("face");
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeDriverLicenseResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //名片识别
//    public static void testRecognizeBusinessCard() throws Exception {
//        System.out.println("--------  名片识别 --------------");
//        RecognizeBusinessCardRequest req = new RecognizeBusinessCardRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeBusinessCardResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //出租车发票识别
//    public static void testRecognizeTaxiInvoice() throws Exception {
//        System.out.println("--------  出租车发票识别 --------------");
//        RecognizeTaxiInvoiceRequest req = new RecognizeTaxiInvoiceRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeTaxiInvoiceResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //VIN码识别
//    public static void testRecognizeVINCode() throws Exception {
//        System.out.println("--------  VIN码识别 --------------");
//        RecognizeVINCodeRequest req = new RecognizeVINCodeRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeVINCodeResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //人脸比对
//    public static void testCompareFace() throws Exception {
//        System.out.println("--------  人脸比对 --------------");
//        CompareFaceRequest req = new CompareFaceRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURLA("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        req.setImageURLB("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/yyy.png");
//        CompareFaceResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //公章识别
//    public static void testRecognizeStamp() throws Exception {
//        System.out.println("--------  公章识别 --------------");
//        RecognizeStampRequest req = new RecognizeStampRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeStampResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //通用识别
//    public static void testRecognizeCharacter() throws Exception {
//        System.out.println("--------  通用识别 --------------");
//        RecognizeCharacterRequest req = new RecognizeCharacterRequest();
//        req.setMinHeight(10);
//        req.setOutputProbability(true);
//        req.setImageURL(
//                "https://viapi-demo.oss-cn-shanghai-internal.aliyuncs.com/viapi-demo/images/RecognizeCharacter/recognizeCharacter_demo.jpg");
//        RecognizeCharacterResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //营业执照识别
//    public static void testRecognizeBusinessLicense() throws Exception {
//        System.out.println("--------  营业执照识别 --------------");
//        RecognizeBusinessLicenseRequest req = new RecognizeBusinessLicenseRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeBusinessLicenseResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //户口页识别
//    public static void testRecognizeAccountPage() throws Exception {
//        System.out.println("--------  户口页识别 --------------");
//        RecognizeAccountPageRequest req = new RecognizeAccountPageRequest();
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeAccountPageResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
//    //行驶证识别
//    public static void testRecognizeDrivingLicense() throws Exception {
//        System.out.println("--------  行驶证识别 --------------");
//        RecognizeDrivingLicenseRequest req = new RecognizeDrivingLicenseRequest();
//        req.setSide("face");
//        // 注意：下面的链接换成自有的oss链接
//        req.setImageURL("https://viapi-demo.oss-cn-shanghai.aliyuncs.com/viapi-demo/xxx.png");
//        RecognizeDrivingLicenseResponse resp = getAcsResponse(req);
//        printResponse(req.getSysActionName(), resp.getRequestId(), resp);
//    }
    public static void printResponse(String actionName, String requestId, AcsResponse  data) {
        System.out.println(String.format("actionName=%s, requestId=%s, data=%s", actionName, requestId,
                JSON.toJSONString(data) ));
    }
}
