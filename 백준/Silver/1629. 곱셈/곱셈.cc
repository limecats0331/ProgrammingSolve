#include "bits/stdc++.h"

using namespace std;

using ll = long long;

ll get_mod(ll a, ll b, ll m) {
    if (b == 1) return a % m;
    ll tmp = get_mod(a, b / 2, m);
    tmp = tmp * tmp % m;
    if (b % 2 == 0) return tmp;
    return tmp * a % m;
}

int main() {
    ios::sync_with_stdio(false), cin.tie(nullptr);

    int A, B, C;
    cin >> A >> B >> C;
    cout << get_mod(A, B, C);

    return 0;
}