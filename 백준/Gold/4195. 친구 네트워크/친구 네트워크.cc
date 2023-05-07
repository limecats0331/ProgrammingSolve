#include "bits/stdc++.h"

using namespace std;

int T, F;
map<string, string> root;
map<string, int> set_cnt;

string find_root(string p) {
    if (root[p] == p) return p;
    return root[p] = find_root(root[p]);
}

void union_set(string p1, string p2) {
    p1 = find_root(p1);
    p2 = find_root(p2);
    if (p1 == p2) return;
    root[p1] = p2;
    set_cnt[p2] += set_cnt[p1];
    set_cnt.erase(p1);
}

int main() {
    cin.tie(nullptr);
    ios::sync_with_stdio(false);

    cin >> T;
    for (int i = 0; i < T; ++i) {
        cin >> F;
        root.clear();
        set_cnt.clear();
        for (int j = 0; j < F; ++j) {
            string p1, p2;
            cin >> p1 >> p2;
            if (root.find(p1) == root.end()) {
                root.insert({p1, p1});
                set_cnt.insert({p1, 1});
            }
            if (root.find(p2) == root.end()) {
                root.insert({p2, p2});
                set_cnt.insert({p2, 1});
            }
            union_set(p1, p2);
//            cout << "p1=" << p1 << " p2=" << p2 << "\n";
//            cout << "root p1=" << find_root(p1) << " root p2=" << find_root(p2) << "\n";
            cout << set_cnt[find_root(p1)] << "\n";
        }
    }

    return 0;
}
