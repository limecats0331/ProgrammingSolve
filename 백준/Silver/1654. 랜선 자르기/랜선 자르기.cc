#include "bits/stdc++.h"

#define ll long long

using namespace std;

int K, N;
vector<ll> lopes;

bool def(ll len) {
    int answer = 0;
    for (int i = 0; i < K; ++i) {
        answer += lopes[i] / len;
    }
    return N > answer;
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> K >> N;
    for (int i = 0; i < K; ++i) {
        ll tmp;
        cin >> tmp;
        lopes.push_back(tmp);
    }
    sort(lopes.begin(), lopes.end());

    ll st = 0;
    ll en = pow(2, 32) - 1;
    ll mid;
    while (st < en) {
        mid = (st + en + 1) / 2;
//        cout << "st : " << st << " mid : " << mid << " en : " << en << "\n";
        if (def(mid)) {
            en = mid - 1;
        } else {
            st = mid;
        }
    }
    
    cout << st;
}
