import 'package:auth_intern/features/data/internet_side/web_services.dart';
import 'package:auth_intern/features/data/models/Users.dart';
import 'package:auth_intern/features/data/repos/users_repo.dart';
import 'package:auth_intern/features/domain/bloc/users_cubit.dart';
import 'package:flutter/material.dart';
import 'package:flutter_bloc/flutter_bloc.dart';
import 'package:go_router/go_router.dart';

import 'features/screens/home_screen.dart';
import 'features/screens/login_screen.dart';
import 'features/screens/register_screen.dart';

class AppRouter {
  static late UsersRepo usersRepo;
  static late UsersCubit usersCubit;

  AppRouter() {
    usersRepo = UsersRepo(WebServices());
    usersCubit = UsersCubit(usersRepo);
  }

  static final GoRouter router = GoRouter(
    routes: [
      GoRoute(
        path: '/',
        builder: (BuildContext context, GoRouterState state) {
          return BlocProvider(
            create: (BuildContext context) => usersCubit,
            child: const LoginScreen(),
          );
        },
      ),
      GoRoute(
        path: '/registerationScreen',
        builder: (BuildContext context, GoRouterState state) {
          return const RegisterationScreen();
        },
      ),
      GoRoute(
        path: '/homeScreen/:user', // Define a parameter in the path
        builder: (BuildContext context, GoRouterState state) {
          return const HomeScreen();
        },
      ),
    ],
  );

}
