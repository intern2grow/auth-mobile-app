import 'package:dio/dio.dart';

import '../../../constants.dart';

class WebServices {
  late Dio dio;
  WebServices (){
    BaseOptions options = BaseOptions(
      baseUrl: kBaseUrl,
      receiveDataWhenStatusError: true,
      connectTimeout: const Duration(seconds: 20),
      receiveTimeout: const Duration(seconds: 20),
    );
    dio = Dio(options);
  }
  Future<List<dynamic>> fetchAccounts() async {
    try {
      Response response = await dio.get('users');
      print(response.data);
      return response.data['users'];
    } catch (e) {
      print('Could not fetch data...\nError is: ${e.toString()}');
      return [];
    }
  }
}

