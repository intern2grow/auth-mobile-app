import 'package:auth_intern/features/data/internet_side/web_services.dart';
import '../models/Users.dart';

class UsersRepo {
  final WebServices webServices;

  UsersRepo(this.webServices);

  Future<List<User>> fetchUsers() async {
    print('Iam in the repo right now');
    final users = await webServices.fetchAccounts();
    return users.map((user) => User.fromJson(user)).toList();
  }
}