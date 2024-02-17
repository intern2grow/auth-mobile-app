import 'package:auth_intern/features/data/repos/users_repo.dart';
import 'package:flutter/cupertino.dart';
import 'package:flutter_bloc/flutter_bloc.dart';

import '../../data/models/Users.dart';

part 'users_state.dart';

class UsersCubit extends Cubit<UsersState> {
  final UsersRepo usersRepo;
  List<User> users = [];

  UsersCubit(this.usersRepo) : super(UsersInitial());

  List<User> fetchUsers() {
    usersRepo.fetchUsers().then((users) {
      emit(UsersLoaded(users));
      this.users = users;
    });
    return users;
  }
}
